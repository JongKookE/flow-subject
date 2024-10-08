const fixList = ["bat", "cmd", "com", "cpl", "exe", "scr", "js"];

// 파일 업로드
const upload = async () => {
    const file = document.getElementById("input-file").files[0];
    const fileType = file.name.split(".").pop();
    if(!file){
        alert("파일을 선택해 주세요");
        return;
    }

    const res = await getFileTypes();

    for (const element of res) {
        if (element.fileType === fileType) {
            alert("제한된 확장자입니다.");
            return;
        }
    }

    const formData = new FormData();

    formData.append('file', file);

    try {
        const response = await fetch('api/files', {
            method: 'POST',
            body: formData
        });
        const result = await response.json();
        alert(result.message + " 무사히 업로드 되었습니다!");
    } catch (error) {
        console.error(error);
        alert(error.message);
    }
}

// 고정 확장자 체크
const checkBoxInsert = async (event) => {
    try {
        // 서버에 POST 요청을 보냅니다.
        const response = await fetch('api/types', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                "fileType": event.value,
                "isSaved": event.checked,
            })
        });
    } catch (error) {
        console.error('Error:', error);
        alert('에러가 발생했습니다.');
    }
}

const checkboxs= document.querySelectorAll("#checkbox-list input[type='checkbox']")

// checkbox에 일괄적으로 함수 적용
checkboxs.forEach(function(checkbox) {
        checkbox.addEventListener('change', function(){
            checkBoxInsert(this);
        });
});

// 확장자 부르기
const refreshExtension = async () => {
    const exposeDiv = document.getElementById("expose-extension");
    await getFileTypes().then((result) => {
        result.forEach(res => {
            if(fixList.includes(res.fileType)) document.getElementById(res.fileType).checked = res.saved;
            else {
                const newButton = document.createElement("button");
                newButton.innerText = res.fileType + " X";
                newButton.classList.add("btn_close");
                newButton.id = res.fileType;
                newButton.onclick = () => updateExtension(res.fileType);
                exposeDiv.appendChild(newButton);
            }
        })
    })
}

const getFileTypes = async () => {
    try{
        const response = await fetch('api/types', {
            method: "GET",
            headers: {'Content-Type': 'application/json'}
        })
        return await response.json();
    } catch(e){
        console.error('Error:', e);
    }
}

const updateExtension = async (event) => {
    let extensionName;
    let flag = false;
    if(event.id === "add-extension-button") {
        extensionName = document.getElementById("customExtension").value;
        if(extensionName.length <= 0) {
            alert("확장자가 없습니다.");
            return;
        }
        flag = true;
    }
    else extensionName = event;

    try{
        const response = await fetch("api/types", {
            method: "POST",
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                fileType: extensionName,
                isSaved: flag,
            }),
        })
        const result = await response.json();

        if(result.saved) alert(result.name + " 확장자가 추가되었습니다.");
        else alert(result.name + " 확장자가 제거 되었습니다..");
        if(fixList.includes(extensionName)) document.getElementById(extensionName).checked = false;
        else document.getElementById(extensionName).remove();
    } catch (error) {
        console.error('Error:', error);
    } finally {
        document.getElementById("customExtension").value = "";
        await refreshExtension();
    }
}

const validMaxSize = (input) => {
    const maxSize = input.maxLength;
    const currentSize = input.value.length;
    if(currentSize >= maxSize){
        alert("확장자의 최대 입력 길이는 20자리입니다.");
    }
}

window.onload = refreshExtension;
