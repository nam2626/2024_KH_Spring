import { Link } from "react-router-dom";
import '../css/Header.css';
export default function Header() {
  return (
    <>
      <header>
        <nav>
          <ul>
            <li>
              <Link to="/">홈</Link>
            </li>  
            <li>
              <Link to="/register">회원등록</Link>
            </li>  
          </ul>  
        </nav>  
      </header>    
    </>
  );
}