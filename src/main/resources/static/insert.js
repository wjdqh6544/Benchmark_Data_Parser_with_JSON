function htmlDecode(code) {
    var e = document.createElement('textarea');
    e.innerHTML = code;
    return e.value;
}

function updateBenchmarkOrder(){
}

function updateBenchmarkInfo() {
    var productType = document.getElementById("productType");
    var benchmarkPlatform = document.getElementById("benchmarkPlatform");
    var decodedData = htmlDecode(document.getElementById("selectedData").textContent);

    try{
        selected = JSON.parse(decodedData);
        console.log(selected);
    } catch (e) {
        console.error("Error Parsing JSON: ", e);
    }

    benchmarkPlatform.innerHTML = "";

    var selectedType = productType.value;
    console.log(selectedType);
    if (selected[selectedType]) {
        selected[selectedType].forEach(function(platform) {
            var optionElement = document.createElement("option");
            optionElement.value = platform;
            optionElement.textContent = platform;
            benchmarkPlatform.appendChild(optionElement);
        });
    } else {
        var optionElement = document.createElement("option");
        optionElement.value = "NULL";
        optionElement.textContent = "== Choose Benchmark Platform ==";
        benchmarkPlatform.appendChild(optionElement);
    }
}