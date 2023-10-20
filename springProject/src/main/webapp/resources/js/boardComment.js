/**
 * 
 */

console.log(bnoVal);

async function postCommentToserver(cmtData) {
    //이 펑션 쓸때 밑에 메소드 cmtData(받는값)
    try{
        //try 블록: 예외가 발생할 가능성이 있는 코드를 감싸는 블록입니다. 
        //예외가 발생하면 catch 블록으로 제어가 이동합니다.
        const url="/comment/post";
        //url 상수: 서버로 요청을 보낼 엔드포인트(경로)를 저장하는 상수입니다.
        const config={
            //상수: fetch 요청의 설정을 정의하는 객체입니다.
            // 이 객체에는 요청 방법, 헤더 설정 및 데이터(body)가 포함됩니다.
            method:"post",
            headers: {
                'content-type' : 'application/json; charset=utf-8'
            },
            body:JSON.stringify(cmtData)
        };
        const resp=await fetch(url,config);
        //네트워크 요청을 수행하는 함수로, 서버에 요청을 보내는 역할을 합니다. 
        //await를 사용하여 이 요청이 완료될 때까지 대기합니다.
        const result=await resp.text(); //isOK
        //서버 응답의 내용을 텍스트로 파싱합니다. 이 작업도 await를 사용하여 비동기로 처리되며, 
        //결과를 result 상수에 저장합니다.
        // 주로 서버 응답의 성공 여부를 나타내는 텍스트 데이터를 받습니다.
        return result;
    }catch(error) {
        console.log(error)
    }
}
document.getElementById('cmtPostBtn').addEventListener('click',()=>{
    //HTML 문서에서 cmtPostBtn이라는 아이디를 가진 요소를 찾습니다. 
    //이 요소는 클릭 이벤트를 감시하고자 하는 버튼 또는 엘리먼트입니다.
    console.log('11');
    const cmtText=document.getElementById('cmtText').value;
    //cmtText 변수에는 cmtText라는 아이디를 가진 요소의 
    //value 속성(입력된 텍스트 내용)이 저장됩니다. 
    //이것은 사용자가 입력한 댓글 내용을 나타냅니다.
    const cmtWriter=document.getElementById('cmtWriter').innerText;
    //detail에서 span으로 사용하면 innertext 로 보내고, input으로 보내면 value
    //cmtWriter 변수에는 cmtWriter라는 아이디를 가진 요소의 innerText 속성(내용)이 저장됩니다. 
    //이것은 댓글 작성자의 이름 또는 내용을 나타냅니다.
    if(cmtText==""||cmtText==null){
        alert("댓글을 입력해주세요.");
        document.getElementById('cmtText').focus();
        //댓글 입력 필드에 포커스를 줍니다.
        return false;
    }
    else{
        let cmtData={
            bno:bnoVal,
            //처음 누르자 마자 들어감
            writer:cmtWriter,
            content:cmtText
        }
        console.log(cmtData);

        postCommentToserver(cmtData).then(result=>{
            //postCommentToServer 함수를 호출하고, cmtData 객체를 함수에 전달합니다. 
            //이 함수는 이전 코드에서 정의한 서버에 댓글을 게시하는 비동기 함수입니다.
            console.log(result);
            
            if(result==1) {
                alert("입력완료");
                console.log("얜 비엔오" + bnoVal);
                getCommentList(bnoVal); //댓글 뿌리는애
                //getCommentList 함수를 호출하여 댓글 목록을 가져오는 것으로 보입니다. 
                //bnoVal은 게시물 번호를 나타내며, 
                //이를 이용하여 해당 게시물에 대한 댓글 목록을 가져올 수 있습니다.
            }
        })
    }
})






//서버에 댓글 리스트를 달라 요청 구간 
async function spreadCommentListFromServer(bno) {
    try{
        const resp = await fetch('/comment/'+bno);
        const result = await resp.json();
        return result;
        
    }catch(err) {
        console.log(err);
    }
}


function getCommentList(bno){
    spreadCommentListFromServer(bno).then(result =>{


        let tbody = document.getElementById('cmtArea');
        if(result.length > 0) {

            tbody.innerHTML="";
            for(let i = 0; i < result.length; i++) {

                
                let str = `<tr data-cno="${result[i].cno}">`
                str += `<td >${result[i].cno}</td>`
                str += `<td>${result[i].wrtier}</td>`
                str += `<td>${result[i].content}</td>`
                str += `<td>${result[i].modAt}</td>`
                str += `<td><button type="button" class="modBtn" data-bs-toggle="modal" data-bs-target="#myModal">>%</button></td>`
                str += `<td><button type="button" class="delBtn">x</button></td>`
                str += `</tr>`

                tbody.innerHTML+= str;
            }
        }
        else{
            let td = `<td>CommentListEmpty</td>`

            tbody.innerHTML = td;
        }
    })
}

async function removeCommentToServer(cno) {
    
    try{
        const url='/comment/' + cno;
        const config = {
            method : 'delete'
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    }catch(err) {
        console.log(err);
    }
}

document.addEventListener('click', (e)=>{
    if(e.target.classList.contains('delBtn')) {
        
        let tr = e.target.closest('tr')
        let conVal = tr.dataset.cno;

        removeCommentToServer(conVal).then(result =>{
            if(result == 1) {
                alert("댓글 삭제");
            }
            getCommentList(bnoVal);
        })
    }


})