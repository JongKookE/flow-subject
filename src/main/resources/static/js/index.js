const fixList = ["bat", "cmd", "com", "cpl", "exe", "scr", "js"];

const upload = async () => {
    const file = document.getElementById("input-file").files[0];
    const fileType = getUploadType();
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
        // 서버에 POST 요청을 보냅니다.
        const response = await fetch('api/files', {
            method: 'POST',
            body: formData
        });
        const result = await response.json();
        // 응답 메시지를 alert로 표시합니다.
        alert(result.message + " 무사히 업로드 되었습니다!");
    } catch (error) {
        console.error('Error:', error);
        alert('파일 업로드 중 오류가 발생했습니다.');
    }
}
/** TODO
 * 인풋값을 기준으로 한다면, 파일이 선택되고, 확장자를 추가 및 삭제했을때 반영이 어려움
 * const inputChange = () => {
 *     const file = document.getElementById("input-file").files[0];
 *     const typenameSeperate = file.name.split(".");
 *     const typename = typenameSeperate.length > 1 ? typenameSeperate[typenameSeperate.length - 1] : "";
 *     getFileTypes().then((res) => {
 *         res.forEach(item => {
 *             if (item.fileType === typename) {
 *                 alert("제한된 확장자입니다!");
 *                 document.getElementById("upload-btn").disabled = true;
 *                 return;
 *             }
 *         })
 *     })
 * }
 */

const checkBoxChange = async (event) => {
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
        const result = await response.json();
        // 응답 메시지를 alert로 표시합니다.
        alert(result.name + "가 " + result.saved + "로 변경되었습니다.");
    } catch (error) {
        console.error('Error:', error);
        alert('에러가 발생했습니다.');
    }
}

const checkboxs= document.querySelectorAll("#checkbox-list input[type='checkbox']")

// checkbox에 일괄적으로 함수 적용
checkboxs.forEach(function(checkbox) {
        checkbox.addEventListener('change', function(){
            checkBoxChange(this);
        });
});

const checkBoxRefresh = async () => {
    try{
        const response = await fetch('api/types', {
            method: "GET",
            headers: {'Content-Type': 'application/json'}
        })
        const result = await response.json();
        result.forEach(res => {
            if(fixList.includes(res.fileType)) document.getElementById(res.fileType).checked = res.saved;
        })
    } catch(e){
        console.error('Error:', e);
    }
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

const getUploadType = () => {
    const file = document.getElementById("input-file").files[0];
    const typenameSeperate = file.name.split(".");
    return typenameSeperate.length > 1 ? typenameSeperate[typenameSeperate.length - 1] : "";
}

window.onload = checkBoxRefresh;
