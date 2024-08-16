const upload = async () => {
    const file = document.getElementById("input-file").files[0];
    if(!file){
        alert("파일을 선택해 주세요");
        return;
    }
    const formData = new FormData();

    formData.append('file', file);

    try {
        // 서버에 POST 요청을 보냅니다.
        const response = await fetch('/files', {
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

const inputChange = () => {
    console.log(document.getElementById("input-file").files[0]);
}