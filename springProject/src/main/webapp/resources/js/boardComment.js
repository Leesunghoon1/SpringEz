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
                document.getElementById('cmtText').value='';
                getCommentList(bnoVal); //댓글 뿌리는애
                //getCommentList 함수를 호출하여 댓글 목록을 가져오는 것으로 보입니다. 
                //bnoVal은 게시물 번호를 나타내며, 
                //이를 이용하여 해당 게시물에 대한 댓글 목록을 가져올 수 있습니다.
            }
        })
    }
})






//서버에 댓글 리스트를 달라 요청 구간 
async function spreadCommentListFromServer(bno, page) {
    try{
        const resp = await fetch('/comment/'+bno+'/'+page);
        const result = await resp.json();
        return result;
        
    }catch(err) {
        console.log(err);
    }
}


function getCommentList(bno, page=1){
    //일단 뿌리면 1 페이지 첫페이지를 뿌려라
    spreadCommentListFromServer(bno, page).then(result =>{
        console.log(result); //ph 객체 pgvo, totalcount, cmtList


        let tbody = document.getElementById('cmtArea');
        if(result.cmtList.length > 0) {

            if(page == 1) {
                //1페이지 일때만 
                tbody.innerHTML="";
            }

            for(let i = 0; i < result.cmtList.length; i++) {

                
                let str = `<tr data-cno="${result.cmtList[i].cno}" data-content="${result.cmtList[i].content}" >`;
                str += `<td>${result.cmtList[i].cno}</td>`
                str += `<td>${result.cmtList[i].wrtier}</td>`
                str += `<td>${result.cmtList[i].content}</td>`
                str += `<td>${result.cmtList[i].modAt}</td>`
                str += `<td><button type="button" class="modBtn" data-bs-toggle="modal" data-bs-target="#myModal">%</button></td>`
                str += `<td><button type="button" class="delBtn">x</button></td>`
                str += `</tr>`

                tbody.innerHTML+= str;
            }

            //댓글 페이징 코드
            let moreBtn = document.getElementById('moreBtn');
            console.log(moreBtn);
            //db에서 pgvo + list 같이 가져와야 값을 줄 수 있음.
            if(result.pgvo.pageNo < result.endPage || result.next) {
                //다음페이지가 있는지 없는지 확인
                moreBtn.style.visibility = 'visible';
                moreBtn.dataset.page = page + 1;
            }else{
                moreBtn.style.visibility = 'hidden';
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
    }else if(e.target.classList.contains('modBtn')) {

        let tr = e.target.closest('tr');

        let cmtText = tr.dataset.content;
        //nextSibling() : 같은 부모의 다음 형제 (즉 밑에 있는 객체를 반환)
        console.log(cmtText);
        document.getElementById('cmtTextMod').value = cmtText;
        //기존내용 모달창에 반영 (수정하기 편하게...)
        //cmttext에 선택한 벨류를 넣기
        
        document.getElementById('cmtModBtn').setAttribute('data-cno', tr.dataset.cno);
        //cmtModBtn에 data-cno 달고 가기
        //왜냐면 cno 버튼을 쓰려고

    }else if(e.target.id == 'cmtModBtn') {
        let cmtDataMod={
            cno : e.target.dataset.cno,
            content : document.getElementById('cmtTextMod').value
        };
        console.log(cmtDataMod);
        editCommenntTosever(cmtDataMod).then(result=>{
            if(parseInt(result)){
                //모달창 닫기
                

                getCommentList(bnoVal);
                document.querySelector('.btn-close').click();
            }
        })
    }else if(e.target.id == 'moreBtn') {
        getCommentList(bnoVal, parseInt(e.target.dataset.page));
    }
})

async function editCommenntTosever(cmtDataMod) {
    try {
        const url = '/comment/'+cmtDataMod.cno;
        const config = {
            method: 'put',
            headers: {
                'Content-Type' : 'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtDataMod)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;

    } catch (error) {
        console.log(error);
    }

}