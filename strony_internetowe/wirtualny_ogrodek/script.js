document.addEventListener('DOMContentLoaded', function() {
    const MAX_PLANTS_PER_BED = 5;
    const WATER_DROPS_PER_ACTION = 3;

    let beds = [
        {
            id: 'bed-1',
            plants: [null, null, null, null, null]
        },
        {
            id: 'bed-2',
            plants: [null, null, null, null, null]
        }
    ];

    let selectedPlantType = null;
    let selectedPlant = null;
    let selectedSlot = null;
    let plantingMode = false;

    const plantTypes = {
        hyacinth: {
            name: 'Hiacynt',
            maxWater: 8,
            colors: ['pink', 'purple'],
            stages: [
                { name: 'Cebulka', image: 'img/hiacynt_cebulka.png'},
                { name: 'Mały', image: 'img/hiacynt_maly.png'},
                { name: 'Średni', image: 'img/hiacynt_sredni.png'},
                { name: 'Duży', image: 'img/hiacynt_duzy.png'},
                { name: 'Kwitnący', image: 'img/hiacynt_kwitnacy_pink.png'},
                { name: 'Przelany', image: 'img/hiacynt_zwiedly.png'}
            ]
        },
        daffodil: {
            name: 'Żonkil',
            maxWater: 6,
            colors: ['yellow'],
            stages: [
                { name: 'Cebulka', image: 'img/zonkil_cebulka.png'},
                { name: 'Mały', image: 'img/zonkil_maly.png'},
                { name: 'Średni', image: 'img/zonkil_sredni.png'},
                { name: 'Duży', image: 'img/zonkil_duzy.png'},
                { name: 'Kwitnący', image: 'img/zonkil_kwitnacy.png'},
                { name: 'Przelany', image: 'img/zonkil_zwiedly.png'}
            ]
        },
        tulip: {
            name: 'Tulipan',
            maxWater: 7,
            colors: ['yellow', 'red', 'purple'],
            stages: [
                { name: 'Cebulka', image: 'img/tulipan_cebulka.png'},
                { name: 'Mały', image: 'img/tulipan_maly.png'},
                { name: 'Średni', image: 'img/tulipan_sredni.png'},
                { name: 'Duży', image: 'img/tulipan_duzy_yellow.png'},
                { name: 'Kwitnący', image: 'img/tulipan_kwitnacy_yellow.png'},
                { name: 'Przelany', image: 'img/tulipan_zwiedly_yellow.png'}
            ]
        }
    };

    const hyacinthBtn = document.getElementById('hyacinth');
    const daffodilBtn = document.getElementById('daffodil');
    const tulipBtn = document.getElementById('tulip');

    const waterBtn = document.getElementById('water');
    const fertilizeBtn = document.getElementById('fertilize');
    const digUpBtn = document.getElementById('dig-up');
    const rescueBtn = document.getElementById('rescue');

    initializeGarden();

    // przyciski wyboru roślin
    hyacinthBtn.addEventListener('click', function() {
        selectPlantType('hyacinth');
    });

    daffodilBtn.addEventListener('click', function() {
        selectPlantType('daffodil');
    });

    tulipBtn.addEventListener('click', function() {
        selectPlantType('tulip');
    });

    // przyciski akcji
    waterBtn.addEventListener('click', waterPlants);
    fertilizeBtn.addEventListener('click', fertilizePlant);
    digUpBtn.addEventListener('click', digUpPlant);
    rescueBtn.addEventListener('click', rescuePlants);

    function initializeGarden() {
        const garden = document.querySelector('.garden');
        garden.innerHTML = '';

        for (let bedIndex = 0; bedIndex < beds.length; bedIndex++) {
            const bed = beds[bedIndex];

            // tworzenie div dla grządki
            const bedElement = document.createElement('div');
            bedElement.classList.add('bed');
            bedElement.id = bed.id;

            // nagłówek grządki
            const bedTitle = document.createElement('h3');
            bedTitle.textContent = `Grządka ${bedIndex + 1}`;

            // container na rośliny
            const plantsContainer = document.createElement('div');
            plantsContainer.classList.add('plants-container');

            bedElement.appendChild(bedTitle);
            bedElement.appendChild(plantsContainer);

            garden.appendChild(bedElement);
        }

        renderPlants();
    }

    function selectPlantType(type) {
        // odznaczenie, jeśli kliknięto ponownie na ten sam przycisk
        if (selectedPlantType === type && plantingMode) {
            hyacinthBtn.classList.remove('selected');
            daffodilBtn.classList.remove('selected');
            tulipBtn.classList.remove('selected');

            hyacinthBtn.classList.remove('planting-mode');
            daffodilBtn.classList.remove('planting-mode');
            tulipBtn.classList.remove('planting-mode');

            selectedPlantType = null;
            plantingMode = false;
        } else {
            // usunięcie starych zaznaczeń
            hyacinthBtn.classList.remove('selected');
            daffodilBtn.classList.remove('selected');
            tulipBtn.classList.remove('selected');

            // reset trybu sadzenia
            hyacinthBtn.classList.remove('planting-mode');
            daffodilBtn.classList.remove('planting-mode');
            tulipBtn.classList.remove('planting-mode');

            selectedPlantType = type;
            document.getElementById(type).classList.add('selected');
            document.getElementById(type).classList.add('planting-mode');
            plantingMode = true;

            if (selectedPlant) {
                selectedPlant = null;
                const selectedPlants = document.querySelectorAll('.plant.selected');
                for (let i = 0; i < selectedPlants.length; i++) {
                    selectedPlants[i].classList.remove('selected');
                }
            }

            // odznaczenie zaznaczonego slotu
            if (selectedSlot) {
                selectedSlot = null;
                const selectedSlots = document.querySelectorAll('.plant-slot.selected');
                for (let i = 0; i < selectedSlots.length; i++) {
                    selectedSlots[i].classList.remove('selected');
                }
            }
        }

        updateButtonStates();
    }

    function getRandomColor(plantType) {
        const colors = plantTypes[plantType].colors;  // pobranie dostępnych kolorów
        const randomIndex = Math.floor(Math.random() * colors.length);  // losowanie indeksu
        return colors[randomIndex];
    }

    function plantInSlot(bedId, slotIndex) {
        if (!selectedPlantType || !plantingMode) {
            return;
        }

        // szukanie odpowiedniej grządki
        let bedIndex = -1;
        for (let i = 0; i < beds.length; i++) {
            if (beds[i].id === bedId) {
                bedIndex = i;
                break;
            }
        }
        if (bedIndex === -1) {
            return;
        }

        // sprawdzenie, czy miejsce jest puste
        if (beds[bedIndex].plants[slotIndex] !== null) {
            alert('To miejsce jest już zajęte. Wybierz puste miejsce.');
            return;
        }

        const newPlant = {
            id: `plant-${Date.now()}`,
            type: selectedPlantType,
            waterCount: 0,
            stage: 0,
            bedId: bedId,
            slotIndex: slotIndex,
            color: getRandomColor(selectedPlantType)
        };

        beds[bedIndex].plants[slotIndex] = newPlant;


        renderPlants();
        updateButtonStates();
    }

    function renderPlants() {
        for (let b = 0; b < beds.length; b++) {
            const bed = beds[b];

            const bedElement = document.getElementById(bed.id);
            const plantsContainer = bedElement.querySelector('.plants-container');

            plantsContainer.innerHTML = '';

            // siatka dla równomiernego rozmieszczenia roślin
            const plantsGrid = document.createElement('div');
            plantsGrid.classList.add('plants-grid');

            // dodanie slotów
            for (let i = 0; i < MAX_PLANTS_PER_BED; i++) {
                const plantSlot = document.createElement('div');
                plantSlot.classList.add('plant-slot');

                // zapis danych o grządce i indeksie
                plantSlot.setAttribute('data-bed-id', bed.id);
                plantSlot.setAttribute('data-slot-index', i);

                // zaznaczenie dla wybranego slotu
                if (selectedSlot && selectedSlot.bedId === bed.id && selectedSlot.slotIndex === i) {
                    plantSlot.classList.add('selected');
                }

                // obsługa kliknięcia na slot
                plantSlot.addEventListener('click', function(event) {
                    // czy slot zawiera roślinę
                    const plant = beds[b].plants[i];

                    if (event.target === plantSlot) {
                        event.stopPropagation();
                    }

                    // jeśli jest tryb sadzenia i slot jest pusty
                    if (plantingMode && plant === null) {
                        plantInSlot(bed.id, i);
                    }
                    // jeśli jest tryb sadzenia, ale slot jest zajęty
                    else if (plantingMode && plant !== null) {
                        alert('To miejsce jest już zajęte. Wybierz puste miejsce.');
                    }
                    else {
                        selectSlot(bed.id, i);
                    }
                });

                if (bed.plants[i] !== null) {
                    const plantElement = createPlantElement(bed.plants[i]);

                    // obsługa kliknięcia na roślinę
                    plantElement.addEventListener('click', function(e) {
                        e.stopPropagation();
                        selectPlant(bed.plants[i]);
                    });

                    plantSlot.appendChild(plantElement);
                }

                plantsGrid.appendChild(plantSlot);
            }

            plantsContainer.appendChild(plantsGrid);
        }
    }

    function createPlantElement(plant) {
        const plantData = plantTypes[plant.type];  // pobranie danych o typie rośliny
        // kopia danych o etapie wzrostu
        const stageData = {
            name: plantData.stages[plant.stage].name,
            image: plantData.stages[plant.stage].image
        };

        if ((plant.type === 'tulip') && plant.color)
            if (plant.stage === 3) {
                stageData.image = `img/tulipan_duzy_${plant.color}.png`;
            } else if (plant.stage === 4) {
                stageData.image = `img/tulipan_kwitnacy_${plant.color}.png`;
            } else if (plant.stage === 5) {
                stageData.image = `img/tulipan_zwiedly_${plant.color}.png`;
            }

        if ((plant.type === 'hyacinth') && plant.color)
            if (plant.stage === 4) {
                stageData.image = `img/hiacynt_kwitnacy_${plant.color}.png`;
            }

        const element = document.createElement('div');
        element.classList.add('plant');
        element.setAttribute('data-id', plant.id);
        element.setAttribute('data-bed-id', plant.bedId);
        element.setAttribute('data-slot-index', plant.slotIndex);

        const img = document.createElement('img');
        img.src = stageData.image;
        element.appendChild(img);

        const info = document.createElement('div');
        info.classList.add('plant-info');

        const nameSpan = document.createElement('div');
        nameSpan.classList.add('plant-name');
        nameSpan.textContent = plantData.name;
        nameSpan.style.fontWeight = 'bold';
        info.appendChild(nameSpan);

        const statusSpan = document.createElement('div');
        statusSpan.classList.add('plant-status');
        statusSpan.textContent = stageData.name;
        statusSpan.style.fontWeight = 'normal';
        info.appendChild(statusSpan);

        const waterSpan = document.createElement('div');
        let displayWaterCount;
        let isOverwatered = false;

        // czy roślina została przelana
        if (plant.waterCount > plantData.maxWater) {
            displayWaterCount = Math.floor(plant.waterCount * 100);
            isOverwatered = true;
        } else if (plant.waterCount >= plantData.maxWater) {
            // nie pokazuj więcej niż jest limit
            displayWaterCount = plantData.maxWater * 100;
        } else {
            // normalna wartość
            displayWaterCount = Math.floor(plant.waterCount * 100);
        }

        const maxWaterDisplay = plantData.maxWater * 100;

        waterSpan.textContent = `${displayWaterCount}/${maxWaterDisplay}`;

        if (isOverwatered) {
            waterSpan.classList.add('overwatered');
        }

        info.appendChild(waterSpan);
        element.appendChild(info);

        return element;
    }

    function selectPlant(plant) {
        // odznaczenie wszystkich zaznaczonych roślin i slotów
        const selectedPlants = document.querySelectorAll('.plant.selected');
        for (let i = 0; i < selectedPlants.length; i++) {
            selectedPlants[i].classList.remove('selected');
        }

        const selectedSlots = document.querySelectorAll('.plant-slot.selected');
        for (let i = 0; i < selectedSlots.length; i++) {
            selectedSlots[i].classList.remove('selected');
        }

        // czy ta sama roślina jest już zaznaczona
        if (selectedPlant && selectedPlant.id === plant.id) {
            // odznaczenie rośliny
            selectedPlant = null;
            selectedSlot = null;
        } else {
            // zaznaczenie rośliny
            selectedPlant = plant;

            // zaznaczenie odpowiedniego slotu
            selectedSlot = {
                bedId: plant.bedId,
                slotIndex: plant.slotIndex
            };

            const slotSelector = `.plant-slot[data-bed-id="${plant.bedId}"][data-slot-index="${plant.slotIndex}"]`;
            const slotElement = document.querySelector(slotSelector);

            if (slotElement) {
                slotElement.classList.add('selected');
            }
        }

        updateButtonStates();
    }

    function selectSlot(bedId, slotIndex) {
        let bedIndex = -1;
        for (let i = 0; i < beds.length; i++) {
            if (beds[i].id === bedId) {
                bedIndex = i;
                break;
            }
        }
        if (bedIndex === -1) {
            return;
        }

        // czy w slocie jest roślina
        const plant = beds[bedIndex].plants[slotIndex];

        // zaznacz roślinę, jeśli istnieje
        if (plant !== null) {
            selectPlant(plant);
            return;
        }

        // czy ten sam slot jest już zaznaczony
        if (selectedSlot && selectedSlot.bedId === bedId && selectedSlot.slotIndex === slotIndex) {
            // odznaczenie slotu
            const selectedSlots = document.querySelectorAll('.plant-slot.selected');
            for (let i = 0; i < selectedSlots.length; i++) {
                selectedSlots[i].classList.remove('selected');
            }
            selectedSlot = null;
        } else {
            // odznaczenie rośliny
            const selectedPlants = document.querySelectorAll('.plant.selected');
            for (let i = 0; i < selectedPlants.length; i++) {
                selectedPlants[i].classList.remove('selected');
            }
            selectedPlant = null;

            // odznaczenie wszystkich zaznaczonych slotów
            const selectedSlots = document.querySelectorAll('.plant-slot.selected');
            for (let i = 0; i < selectedSlots.length; i++) {
                selectedSlots[i].classList.remove('selected');
            }

            // zaznaczenie slotu
            selectedSlot = {
                bedId: bedId,
                slotIndex: slotIndex
            };

            const slotSelector = `.plant-slot[data-bed-id="${bedId}"][data-slot-index="${slotIndex}"]`;
            const slotElement = document.querySelector(slotSelector);

            if (slotElement) {
                slotElement.classList.add('selected');
            }
        }

        updateButtonStates();
    }

    function waterPlants() {
        const alivePlants = [];
        for (let b = 0; b < beds.length; b++) {
            const bed = beds[b];

            for (let p = 0; p < bed.plants.length; p++) {
                const plant = bed.plants[p];

                // jeśli miejsce nie jest puste i roślina nie jest przelana
                if (plant !== null && plant.stage < 5) {
                    alivePlants.push(plant);
                }
            }
        }

        if (alivePlants.length === 0) {
            alert('Nie ma żywych roślin do podlania :(');
            return;
        }

        const dropsPerPlant = WATER_DROPS_PER_ACTION / alivePlants.length;

        for (let i = 0; i < alivePlants.length; i++) {
            const plant = alivePlants[i];
            const plantData = plantTypes[plant.type];

            plant.waterCount += dropsPerPlant;

            if (plant.waterCount > plantData.maxWater) {
                plant.stage = 5;  // przelany
            } else {
                const waterPercentage = plant.waterCount / plantData.maxWater;

                if (waterPercentage < 0.1) {
                    plant.stage = 0;  // cebulka
                } else if (waterPercentage < 0.25) {
                    plant.stage = 1;  // mały
                } else if (waterPercentage < 0.5) {
                    plant.stage = 2;  // średni
                } else if (waterPercentage < 0.75) {
                    plant.stage = 3;  // duży
                } else {
                    plant.stage = 4;  // kwitnący
                }
            }
        }

        renderPlants();
        updateButtonStates();
    }

    function fertilizePlant() {
        let emptySlots = [];

        for (let bedIndex = 0; bedIndex < beds.length; bedIndex++) {
            const bed = beds[bedIndex];

            for (let slotIndex = 0; slotIndex < bed.plants.length; slotIndex++) {
                if (bed.plants[slotIndex] === null) {
                    emptySlots.push({
                        bedId: bed.id,
                        slotIndex: slotIndex,
                        bedIndex: bedIndex
                    });
                }
            }
        }

        if (emptySlots.length === 0) {
            alert('Nie ma wolnych miejsc do posadzenia nowej rośliny :(');
            return;
        }

        const randomSlot = emptySlots[Math.floor(Math.random() * emptySlots.length)];

        const plantTypes = ['hyacinth', 'daffodil', 'tulip'];
        const randomType = plantTypes[Math.floor(Math.random() * plantTypes.length)];

        const newPlant = {
            id: `plant-${Date.now()}`,
            type: randomType,
            waterCount: 0,
            stage: 0,
            bedId: randomSlot.bedId,
            slotIndex: randomSlot.slotIndex,
            color: getRandomColor(randomType)
        };

        beds[randomSlot.bedIndex].plants[randomSlot.slotIndex] = newPlant;

        renderPlants();
        updateButtonStates();
    }

    function digUpPlant() {
        if (!selectedPlant) {
            alert('Wybierz roślinę, którą chcesz wykopać.');
            return;
        }

        let bedIndex = -1;
        for (let i = 0; i < beds.length; i++) {
            if (beds[i].id === selectedPlant.bedId) {
                bedIndex = i;
                break;
            }
        }
        if (bedIndex < 0) {
            return;
        }

        const slotIndex = selectedPlant.slotIndex;
        if (slotIndex < 0 || slotIndex >= MAX_PLANTS_PER_BED) {
            return;
        }

        beds[bedIndex].plants[slotIndex] = null;

        selectedPlant = null;
        selectedSlot = null;

        renderPlants();
        updateButtonStates();
    }

    function rescuePlants() {
        let overwateredPlants = false;

        for (let i = 0; i < beds.length; i++) {
            const bed = beds[i];
            for (let j = 0; j < bed.plants.length; j++) {
                const plant = bed.plants[j];
                if (plant !== null && plant.stage === 5) {
                    plant.stage = 0;
                    plant.waterCount = 0;
                    overwateredPlants = true;
                }
            }
        }

        renderPlants();
        updateButtonStates();
    }

    // aktualizacja stanów przycisków
    function updateButtonStates() {

        let hasOverwateredPlants = false;
        let hasAlivePlants = false;

        for (let i = 0; i < beds.length; i++) {
            const bed = beds[i];
            for (let j = 0; j < bed.plants.length; j++) {
                const plant = bed.plants[j];
                if (plant !== null) {
                    if (plant.stage === 5) {
                        hasOverwateredPlants = true;
                    } else {
                        hasAlivePlants = true;
                    }
                }
            }
        }

        rescueBtn.disabled = !hasOverwateredPlants;
        waterBtn.disabled = !hasAlivePlants;
        digUpBtn.disabled = !selectedPlant;

        let hasEmptySlots = false;

        findEmptySlots:
        for (let i = 0; i < beds.length; i++) {
            const bed = beds[i];

            for (let j = 0; j < bed.plants.length; j++) {
                if (bed.plants[j] === null) {
                    hasEmptySlots = true;
                    break findEmptySlots; // przerwanie obu pętli
                }
            }
        }

        fertilizeBtn.disabled = !hasEmptySlots;
    }

    renderPlants();
    updateButtonStates();
});