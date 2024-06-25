let formRating = document.querySelector("#formRating");

formRating.addEventListener("submit", function(event) {
    event.preventDefault();
    let ratingElement = document.getElementById("rating");
    let reviewElement = document.getElementById("review");

    if (ratingElement && reviewElement) {
        let rating = ratingElement.value;
        let review = reviewElement.value;

        let response  = fetch(baseUrl + "/api/rating/insert", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                idUser: user,
                nameUser: userName,
                idMovie: movie,
                note: rating,
                comment: review
            })
        });
        if(response.ok){
            alert("Avaliação cadastrada com sucesso!");
            ratingElement.value = "";
            reviewElement.value = "";
        } else {
            alert("Erro ao cadastrar avaliação.");
        }
    } else {
        console.error("Elementos com IDs 'rating' e 'review' não foram encontrados.");
    }
});
