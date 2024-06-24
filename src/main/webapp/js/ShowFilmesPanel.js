const PainelFilmsArea = document.querySelector('#PainelFilmsArea');

console.log(PainelFilmsArea);

window.addEventListener('load', async () => {
    const currentUrl = window.location.href;

    let baseUrl = currentUrl.replace(/\/painel.jsp\/?$/, '');

    let url = baseUrl+ '/api/movie/findAll';
    console.log(url)
    const response = await fetch(url);
    const data = await response.json();
    console.log(data);
    data.forEach(filme => {
        PainelFilmsArea.innerHTML += `
        <div
                    class="w-[90%] h-[150px] bg-primary text-white rounded-lg flex flex-row gap-10 justify-between items-center">
                <div class="flex flex-row gap-5 h-full items-center ml-2">
                    <img src="${filme.poster}" alt="Cartaz do Filme X" class="h-[80%] rounded-lg">
                    <div>
                        <h2 class="text-2xl">${filme.title}</h2>
                        <p><span class="font-bold"><a href="">${filme.genre}</a></span></p>
                        <p>Direção: <span class="font-bold"><a href="">${filme.director}</a></span></p>
                        <p>Ano Lançamento: <span class="font-bold">${filme.year}</span></p>
                    </div>
                </div>
                <div class="flex items-center p-5 gap-5">
                    <a href="${baseUrl+'/painel/edit/'+filme.id}" class="hover:-translate-y-1 transition-all duration-150"><i class="fa-solid fa-pen-to-square text-2xl text-white"></i></a>
                    <a href="${baseUrl+'/painel/delete/'+filme.id}" class="hover:-translate-y-1 transition-all duration-150"><i class="fa-solid fa-trash-can text-2xl text-white"></i></a>
                </div>
            </div>`
    })
})