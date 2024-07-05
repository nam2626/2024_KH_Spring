import axios from "axios";
import { useEffect, useRef, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

export default function MemberView() {
  const {memberId} = useParams();
  console.log(memberId);
  const [member, setMember] = useState();
  useEffect(()=>{
    const fetchData = () => {
      axios.get('/member/'+memberId)
      .then(response => {
          console.log(response);
          setMember(response.data);
      });
    }
    fetchData();
  },[]);
  let txtId = useRef();
  let txtPass = useRef();
  let txtName = useRef();
  let txtNick = useRef();
  let selectGrade = useRef();
  let [gradeList, setgradeList] = useState([]);
  const navigate = useNavigate();
  useEffect(() => {
    const readData = async () => {
      axios.get('/grade/list')
      .then(response => {
        console.log(response);
        setgradeList(response.data);
      })
    }
    readData();
  },[]);
  const deleteClick = () => {
    axios.delete('/member/delete',{
      params : {id : txtId.current.value}
  })
    .then(response => {
      console.log(response);
      navigate('/');
    })
  }
  const updateClick = () => {
    const obj = {
      boardMemberId : txtId.current.value,
      boardMemberPasswd : txtPass.current.value,
      boardMemberName : txtName.current.value,
      boardMemberNick : txtNick.current.value,
      boardMemberGrade : selectGrade.current.value,
    }
    axios.put('/member/update',obj)
    .then(response => {
      console.log(response);
      navigate('/');
    })
  }
  if(member == null || gradeList.length == 0){
    return <div>회원 데이터를 로딩중입니다.</div>
  }
  return (
    <div>
      <ul id="register_form">
        <li><input type='text' ref={txtId} readOnly value={member.boardMemberId}/></li>
        <li><input type='password' ref={txtPass} placeholder="암호 입력"/></li>
        <li><input type='text' ref={txtName} placeholder="이름 입력" defaultValue={member.boardMemberName}/></li>
        <li><input type='text' ref={txtNick} placeholder="닉네임 입력" defaultValue={member.boardMemberNick}/></li>
        <li>
            <select ref={selectGrade}>
              {gradeList.map((item,idx) => {
                return (
                  <option value={item.gradeNo} selected={item.gradeNo === member.boardMemberGrade}>{item.gradeName}</option>
                );
              })}
            </select>
        </li>
        <li><button onClick={updateClick}>수정</button><button onClick={deleteClick}>삭제</button><button onClick={()=>window.history.back()}>뒤로가기</button></li>
      </ul>
    </div>
  ); 
}