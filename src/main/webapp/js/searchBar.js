const searchInput = document.getElementById('search');
const resultArea = document.querySelector('#resultArea');
const searchFailCard = document.querySelector('#searchFail');
const searchTextError = document.querySelector('#searchTextError');

searchInput.addEventListener('input', async (e) => {
    const searchTerm = searchInput.value.trim();

    if (e.target.value.length === 0) {
        resultArea.innerHTML = '';
        searchFailCard.style.display = 'none';
        return;
    }

    try {
        const searchUrl = `http://localhost:8080/Movietex_war_exploded/api/movie/simpleSearch?search=${encodeURIComponent(searchTerm)}`;

        const response = await fetch(searchUrl);
        if (!response.ok) {
            throw new Error('Erro ao buscar dados');
        }

        const data = await response.json();

        resultArea.innerHTML = '';

        if (data.length === 0) {
            searchFailCard.style.display = 'block';
            searchTextError.textContent = searchTerm;
        } else {
            console.log(data);
            data.data.forEach(item => {
                const card = document.createElement('div');
                card.classList.add('w-full', 'h-auto', 'flex', 'flex-col', 'justify-center', 'items-center', 'md:items-start', 'md:flex-row', 'md:justify-start', 'gap-2', 'p-4', 'bg-gray-100', 'rounded-lg');

                card.innerHTML = `
                    <img src="${item.poster}" alt="Cartaz do Filme" class="z-10 max-w-[300px] md:w-[30%] rounded-lg">
                    <div class="flex flex-col">
                        <h2 class="text-2xl font-bold">${item.title}</h2>
                        <p><span class="font-bold">${item.genre}</span></p>
                        <p>Direção: <span class="font-bold">${item.director}</span></p>
                        <p>Ano Lançamento: <span class="font-bold">${item.year}</span></p>
                    </div>
                `;

                resultArea.appendChild(card);
            });

            searchFailCard.style.display = 'none';
        }
    } catch (error) {
        console.error('Erro na requisição:', error);
    }
});
