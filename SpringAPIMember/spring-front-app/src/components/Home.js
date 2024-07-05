import { useEffect, useState } from "react"
import axios from 'axios'
import '../css/Home.css'
import { Link } from "react-router-dom";
export default function Home() {
  let [memberList, setMemberList] = useState([]);
  let [gradeList, setgradeList] = useState([]);

  //액시오스로 전체 회원 데이터를 가져오는 코드
  useEffect(() => {
    ///member/list
    //아이디 이름 닉네임 회원등급
    const readData = async () => {
        axios.get('/member/list')
        .then(response => {
          console.log(response);
          setMemberList((v) => [...response.data]);
        })
        axios.get('/grade/list')
        .then(response => {
          console.log(response);
          setgradeList(response.data);
        })
      }
      readData();
    },[]);
    console.log(memberList);
    console.log(gradeList);
    if(memberList.length == 0 || gradeList.length == 0) return;
    return (
    <>
      <h2>회원 목록</h2>
      <table>
        <thead>
          <tr>
            <th>아이디</th>
            <th>이름</th>
            <th>닉네임</th>
            <th>등급</th>
          </tr>
        </thead>
        <tbody>
        {
          memberList.map((item,idx) => {
            return (
              <tr key={idx}>
                <td><Link to={'/member/'+item.boardMemberId}>{item.boardMemberId}</Link></td>
                <td>{item.boardMemberName}</td>
                <td>{item.boardMemberNick}</td>
                <td>
                {
                  gradeList.find((obj) => obj.gradeNo == item.boardMemberGrade).gradeName 
                }
                </td>
              </tr>
            );
          }) 
        }
        </tbody>
      </table>
    </>
  )
}