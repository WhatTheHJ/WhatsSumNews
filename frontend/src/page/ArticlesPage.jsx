import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { getArticles } from "../api/NYTApi"; // API 호출 함수 가져오기
// import axios from "axios";

const ArticlesPage = () => {
  const { period } = useParams(); // URL에서 period 값을 가져옴
  const [articles, setArticles] = useState([]); //setArticles이 저장한 articles값을 맵으로 뿌림
  const [loading, setLoading] = useState(true); // 로딩 상태 관리
  const navigate = useNavigate();
  
  // 페이지네이션 상태
  const [currentPage, setCurrentPage] = useState(1);
  const articlesPerPage = 10; // 한 페이지당 5개씩 표시
  //console.log("Received period:", period);
  //console.log("기사내용", articles) //이때는 문제가 없다

  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true); // API 호출 시작 시 로딩 상태 true
        const data = await getArticles(period); // API 호출
        setArticles(data); // 가져온 기사 데이터 저장
      } catch (error) {
        console.error("Error fetching data:", error.message);
      } finally {
        setLoading(false); // 데이터 로드 완료 후 로딩 상태 false
      }
    }; fetchData();
    //console.log("Fetched articles:", articles);  // 데이터를 콘솔로 확인
  }, [period]); // period 값이 변경될 때마다 API 호출
  
   // 현재 페이지에 해당하는 게시글만 표시
   const indexOfLastArticle = currentPage * articlesPerPage;
   const indexOfFirstArticle = indexOfLastArticle - articlesPerPage;
   const currentArticles = articles.slice(indexOfFirstArticle, indexOfLastArticle);
 
   // 페이지 변경 핸들러
   const nextPage = () => {
     if (indexOfLastArticle < articles.length) {
       setCurrentPage(currentPage + 1);
     }
   };

   const prevPage = () => {
    if (currentPage > 1) {
      setCurrentPage(currentPage - 1);
    }
  };
  
  return (
    <div className="body">
      <div>
      <h1>Articles from the last {period} days</h1>
      <button onClick={() => navigate("/")} style={{ padding: "10px 20px", fontSize: "16px" }}>
      메인으로 이동
    </button>
    </div>

      {/* 로딩 중일 때는 "로딩중..." 메시지 출력 */}
      {loading ? (
        <p>로딩중...</p>
      ) : (
        currentArticles.map((article, index) => {
          // 'Adx_keywords'를 세미콜론 기준으로 분리하여 배열로 만듭니다.
          const keywords = article.adx_keywords ? article.adx_keywords.split(';').map(keyword => keyword.trim()) : [];

          return (
            <div className="article-container" key={index} >
              {/* style={{ border: "1px solid #ddd", padding: "10px", margin: "10px" }} */}
              <h2>{article.title}</h2>
              {/* 섹션, 발행일, abstract */}
              <div style={{ fontStyle: "italic", color: "#555" }}>
                <span>발행일 : {article.published_date} | </span>
                <span>카테고리 : {article.section}</span>
              </div>
              <div>
                <br />
                <span style={{ fontSize: "26px", marginTop: "10px", flex: 1 }}>{article.abstract}</span>
              </div>
              <a href={article.url} target="_blank" rel="noopener noreferrer">
                Read More
              </a>

              <div style={{ display: "flex", alignItems: "center" }}>

  {/* 이미지 렌더링 */}
  {article.image_url && (
    <img
      src={article.image_url} // DB에서 가져온 imageUrl 사용
      alt="Article"
      style={{
        width: "40%",
        height: "auto",
        display: "block",
        marginRight: "50px", // 이미지와 텍스트 사이의 간격
      }}
    />
  )}

  {/* Adx_keywords 렌더링 */}
  {keywords.length > 0 && (
    <div style={{ fontSize: "20px", color: "#555", marginTop: "10px", flex: 1 }}>
      <h3>Related Keywords</h3>
      <ul>
        {keywords.map((keyword, index) => (
          <li key={index}>{keyword}</li>
        ))}
      </ul>
    </div>
  )}
</div>
             
            </div>
            
          );
          
        })
        
      )}
        {/* 페이지네이션 버튼 */}
        <div style={{ marginTop: "20px" }}>
            <button onClick={prevPage} disabled={currentPage === 1}>이전</button>
            <span style={{ margin: "0 10px" }}>페이지 {currentPage}</span>
            <button onClick={nextPage} disabled={indexOfLastArticle >= articles.length}>다음</button>
          </div>
    </div>
  );
};

export default ArticlesPage;
