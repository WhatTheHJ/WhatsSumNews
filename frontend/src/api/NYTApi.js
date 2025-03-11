import axios from "axios";

// Axios 인스턴스 생성 (기본 URL 설정)nytApi 인스턴스를 만들고 기본 URL을 설정하면, 다른 API 요청도 쉽게 추가 가능
const BASE_URL = "http://localhost:8080/nyt/articles"; // Base URL 설정

// NYT API를 호출하는 함수
export const getArticles = async (period) => {

  if (!period || isNaN(Number(period))) {
    console.error("Invalid period in API request:", period);
    return null;
  }
  try {
    const response = await axios.get(`${BASE_URL}/${period}`);
    return response.data;  // 성공 시 기사 데이터 반환
  } catch (error) {
    console.error("Error fetching articles:", error);
    throw error;  // 오류 발생 시 오류를 throw
  }
};

// 다른 API 요청이 필요하면 여기에 추가 가능
//export default NYTApi;