import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { BarChart, Bar, XAxis, YAxis, Tooltip, CartesianGrid, ResponsiveContainer } from "recharts";
import { getArticles } from "../api/NYTApi"; // API 호출 함수 가져오기

const HomePage = () => {
  const navigate = useNavigate();
  const [randomArticle, setRandomArticle] = useState(null);
  const [articles, setArticles] = useState([]);
  const [keywordData, setKeywordData] = useState([]);
  const today = new Date().toISOString().split("T")[0]; 
  //const { period } = useParams();

  useEffect(() => {
    const fetchData = async () => {
      const data = await getArticles(7); // 최근 7일 기준으로 데이터 가져오기
      setArticles(data);
      setKeywordData(getKeywordFrequency(data)); // 키워드 빈도수 계산
    };
    fetchData();
  }, []);

  useEffect(() => {
    const fetchArticle = async () => {
      try {
        const data = await getArticles(1); // 1
        console.log("Fetched Data:", data);
  
        if (data.length > 0) {
          const randomIndex = Math.floor(Math.random() * data.length);
          setRandomArticle(data[randomIndex]);  // 랜덤 기사 설정
        }
      } catch (error) {
        console.error("Error fetching articles:", error);
      }
    };
  
    fetchArticle(); // 비동기 함수 호출
  },[]); // period나 today가 변경될 때마다 실행
  


  const handleButtonClick = (period, today) => {
    // period 값을 전달하면서 ArticlesPage로 이동
    navigate(`/articles/${period}`);
  };

  // ----------------------------------
  const getKeywordFrequency = (articles) => {
    const keywordMap = new Map();
  
    articles.forEach((article) => {
      if (article.adx_keywords) {
        const keywords = article.adx_keywords.split(";").map((kw) => kw.trim());
        keywords.forEach((keyword) => {
          keywordMap.set(keyword, (keywordMap.get(keyword) || 0) + 1);
        });
      }
    });
  
    // 키워드를 빈도수 기준으로 정렬 후 상위 10개 반환
    return [...keywordMap.entries()]
      .sort((a, b) => b[1] - a[1])
      .slice(0, 10)
      .map(([name, value]) => ({ name, value }));
  };
  

  return (
    <div className="main-container">
        {/* 상단 영역 */}
        <div className="top-section">
        <h3>What's Sum News는 뉴욕 타임즈의 인기 토픽과 요약을 제공합니다. </h3>
        </div>


      {/* 왼쪽 영역 */}
      <div className="left-section">
        <div>
        <h3>기간별 토픽</h3>
        <button onClick={() => handleButtonClick(1)}>Last 1 day</button>
        <button onClick={() => handleButtonClick(7)}>Last 7 days</button><br></br><br></br>
        </div>
            <div style={{}}>
            <h2>🔥 뉴욕 타임즈에서 가장 많이 언급된 키워드 (Top 10 | 7일)</h2>
            <ResponsiveContainer width="100%" height={500}> {/* 🔹 세로폭 증가 */}
            <BarChart width={900} height={300} data={keywordData}>
              <CartesianGrid strokeDasharray="3 3" />
              <XAxis
                dataKey="name"
                tick={{ angle: -45, textAnchor: "end" }} // 🔹 X축 레이블을 45도 기울임
                interval={0} // 모든 레이블 표시
                height={200} // 여백 확보
              />
              <YAxis /> 
              <Tooltip />
              <Bar dataKey="value" fill="#8884d8" />
            </BarChart>
            </ResponsiveContainer>
            </div>
      </div>

      {/* 오른쪽 영역 (랜덤 기사) */}
      <div className="right-section">
      <h3>오늘의 랜덤 토픽</h3>
        {randomArticle ? (
          <div className="article-container">
            <h2>{randomArticle.title}</h2>
            <p>{randomArticle.abstract}</p>
            <a href={randomArticle.url} target="_blank" rel="noopener noreferrer">
              Read more
            </a>



             {/* 이미지 렌더링 */}
              {randomArticle.image_url && (
                <img
                  src={randomArticle.image_url} // DB에서 가져온 imageUrl 사용
                  alt="Article"
                  style={{
                    width: "100%",
                    height: "auto",
                    display: "block",
                    marginRight: "50px", // 이미지와 텍스트 사이의 간격
                  }}
                />
              )}
          </div>
        ) : (
          <p>Loading random article...</p>
        )}
      </div>

    </div>
  );
};

export default HomePage;
