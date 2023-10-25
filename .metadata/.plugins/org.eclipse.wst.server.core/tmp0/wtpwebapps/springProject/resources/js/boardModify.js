


async function removeFileFromServer(uuid) {
    try {
        const url = "/board/" + uuid;
        const config = {
            method : 'delete'
        };

        const resp = await fetch(url, config);
        const result = await resp.text();

        return result;
        } catch (error) {
        console.log(error)
    }

}

document.addEventListener('click', (e) =>{
    console.log(e.target);
    if(e.target.classList.contains('remove-x')){
        //삭제작업
        let uuid = e.target.dataset.uuid;
        console.log(uuid);

        //삭제구현
        let div = e.target.closest('div');
        removeFileFromServer(uuid).then(result =>{
            if(result == 1) {
                alert("삭제완료");
                e.target.closest('li').remove();
                location.reload();
            }else{
                alert("삭제안됨");
            }

        })
    }
    
})


