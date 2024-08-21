function displaySavedSuccessfully() {
    var status = JSON.parse(htmlDecode(document.getElementById("enteredDTOJSON").textContent)).saveSuccessfully;
    var html = document.getElementById("saving-result");
    if (status == true) {
        html.textContent = "Saved Successfully.";
    } else {
        html.textContent = "Saved Failed.";
    }
}

// DB 저장 시 Type, Platform 초기화되지 않게 해야 함.
// 표 width 확장 필요
// DB 저장 시 Choose benchmark 초기화되지 않게 해야 함.
// Score 반환 API 호출 시 500 에러 확인필요.

function setSelectionTypeAndPlatform(Type, Platform) {
    var productType = document.getElementById("productType");
    var benchPlatform = document.getElementById("benchmarkPlatform");
    productType.textContent = Type;
    benchPlatform.textContent = Platform;
}

function saveToDB() {
    var inputProductType = document.getElementById("productType").value;
    var inputBenchmarkPlatform = document.getElementById("benchmarkPlatform").value;
    var inputSelectedBench = document.getElementById("benchmarkOrder").value;
    var DTOdata = JSON.parse(htmlDecode(document.getElementById("enteredDTOJSON").textContent));
    DTOdata.productType = inputProductType;
    DTOdata.benchmarkPlatform = inputBenchmarkPlatform;
    DTOdata.selectedBench = inputSelectedBench;
    console.log(DTOdata.productType, DTOdata.benchmarkPlatform);
    $.ajax({
        type: "POST",
        url: "/crawling/saveToDB",
        contentType: "application/json",
        data: JSON.stringify(DTOdata),
        success: function(response) {
            $(".main").html(response);
//            updateBenchmarkOrder();
            displaySavedSuccessfully();
        }
    });
}

function copyURL(hostIP, port) {
    var scoreList = document.getElementById("data-score-table").rows;
    var productType = document.getElementById("productType").value;
    var benchPlatform = document.getElementById("benchmarkPlatform").value;
    var resURL = "http://" + hostIP + ":" + port + "/" + productType + "?benchmark=" + benchPlatform +
                    "&productNames=";
    for (i = 1; i < scoreList.length; i++) {
        resURL += (scoreList[i].cells[1].innerHTML + ",").replace(/ /g, "");
    }
    document.getElementById("data-score-URL-textArea").value = resURL.slice(0, -1);
    document.getElementById("data-score-URL-textArea").select();
    document.execCommand("copy");
}

function getProductList() {
    var scoreList = document.getElementById("data-score-table").rows;
    var resStr = "";
    for (i = 1; i < scoreList.length; i++) {
        resStr += scoreList[i].cells[1].innerHTML + ", ";
    }
    document.getElementById("data-score-list-textArea").value = resStr.slice(0, -2);
}

function updateBenchmarkOrder() {
    var benchOrder = document.getElementById("benchmarkOrder").value;
    var benchScoreTbody = document.getElementById("data-score-table-tbody");
    var benchmarkScoreData = JSON.parse(htmlDecode(document.getElementById("enteredDTOJSON").textContent)).crawledData;
    var totalProduct = document.getElementById("crawling-result-total-product");
    benchScoreTbody.innerHTML = "";

    if (benchmarkScoreData[benchOrder]) {
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
    document.getElementById("productType").value = "NULL";
    updateBenchmarkInfo();
    document.getElementById("data-score-URL-textArea").value = "";
    document.getElementById("data-score-URL-textArea").value = "";
    document.getElementById("saving-result").textContent = "";
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
            updateBenchmarkOrder();
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