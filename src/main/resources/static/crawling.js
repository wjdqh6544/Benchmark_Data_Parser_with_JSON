function updateBenchmarkOrder() {
    var benchOrder = document.getElementById("benchmarkOrder").value;
    var benchScoreTbody = document.getElementById("data-score-table-tbody");
    var benchmarkScoreData = JSON.parse(htmlDecode(document.getElementById("enteredDTOJSON").textContent)).crawledData;
    var totalProduct = document.getElementById("crawling-result-total-product");
    benchScoreTbody.innerHTML = "";

    if (benchmarkScoreData[benchOrder]) {
        var totalProductNum = document.createElement("p");
        totalProduct.textContent = "The number of products: " + Object.keys(benchmarkScoreData[benchOrder]).length;
        Object.entries(benchmarkScoreData[benchOrder]).forEach(([productName, score], index) => {
            var trElement = document.createElement("tr");
            var tdNoElement = document.createElement("td");
            var tdProductNameElement = document.createElement("td");
            var tdScoreElement = document.createElement("td");
            tdNoElement.textContent = (index + 1);
            tdProductNameElement.textContent = productName;
            tdScoreElement.textContent = score;
            trElement.appendChild(tdNoElement);
            trElement.appendChild(tdProductNameElement);
            trElement.appendChild(tdScoreElement);
            benchScoreTbody.appendChild(trElement);
        });
        } else {
        var trElement = document.createElement("tr");
            tdNoElement.textContent = "Benchmark Data does not exists."
            trElement.appendChild(tdNoElement);
            benchScoreTbody.appendChild(trElement);
    }
}

function sendURL() {
    var input = $(".URL-input-box").val();
    var existingDTOText = $("#enteredDTOJSON").text();
    existingDTO = {};

    if (existingDTOText) {
        try {
            existingDTO = JSON.parse(htmlDecode(existingDTOText));
        } catch (e) {
            console.error("ERROR while parsing JSON: ", e);
            return;
        }
    } else {
        console.error("JSON data is not exists.");
        return;
    }

    existingDTO.URL = input;

    $.ajax({
        type: "POST",
        url: "/crawling",
        contentType: "application/json",
        data: JSON.stringify(existingDTO),
        success: function(response) {
            $(".main").html(response);
            updateBenchmarkOrder()
        },
        error: function(xhr, status, error) {
            console.error("ERROR while getting benchmark information: ", status, error);
        }
    });
}

function htmlDecode(code) {
    var e = document.createElement('textarea');
    e.innerHTML = code;
    return e.value;
}

function updateBenchmarkInfo() {
    var productType = document.getElementById("productType");
    var benchmarkPlatform = document.getElementById("benchmarkPlatform");
    var decodedData = htmlDecode(document.getElementById("enteredDTOJSON").textContent);
    try{
        selected = JSON.parse(decodedData).savedStatus;
    } catch (e) {
        console.error("Error Parsing JSON: ", e);
    }

    benchmarkPlatform.innerHTML = "";

    var selectedType = productType.value;

    if (selected[selectedType]) {
        selected[selectedType].forEach(function(benchPlatform) {
            var optionElement = document.createElement("option");
            optionElement.value = benchPlatform;
            optionElement.textContent = benchPlatform;
            benchmarkPlatform.appendChild(optionElement);
        });
    } else {
        var optionElement = document.createElement("option");
        optionElement.value = "NULL";
        optionElement.textContent = "== Choose Benchmark Platform ==";
        benchmarkPlatform.appendChild(optionElement);
    }
}