const PainelFilmsArea = document.querySelector('#PainelFilmsArea');

console.log(PainelFilmsArea);

window.addEventListener('load', async () => {
    let url = window.baseUrl + '/api/movies/findall';
    const response = await fetch(url);
    const data = await response.json();
    data.forEach(filme => {
        PainelFilmsArea.innerHTML += `
        <div
                    class="w-[90%] h-[150px] bg-primary text-white rounded-lg flex flex-row gap-10 justify-between items-center">
                <div class="flex flex-row gap-5 h-full items-center ml-2">
                    <img src="img/capas/divertida_mente.webp" alt="Cartaz do Filme X" class="h-[80%] rounded-lg">
                    <div>
                        <h2 class="text-2xl">Filme X</h2>
                        <p><span class="font-bold"><a href="">Aventura</a></span></p>
                        <p>Direção: <span class="font-bold"><a href="">Kelsey Mann</a></span></p>
                        <p>Ano Lançamento: <span class="font-bold">2024</span></p>
                    </div>
                </div>
                <div class="flex items-center p-5 gap-5">
                    <a href="" class="hover:-translate-y-1 transition-all duration-150"><i class="fa-solid fa-pen-to-square text-2xl text-white"></i></a>
                    <a href="" class="hover:-translate-y-1 transition-all duration-150"><i class="fa-solid fa-trash-can text-2xl text-white"></i></a>
                </div>
            </div>`
    })
})