import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import HomePage from "./page/HomePage";
import ArticlesPage from "./page/ArticlesPage";
import Footer from "./page/Footer";
import Header from "./page/Header";
// -----------------
// import { initializeApp } from "firebase/app";
// import { getAnalytics } from "firebase/analytics";

function App() {

  // const firebaseConfig = {
  //   apiKey: "AIzaSyDGaEytvzWgRs139u4BrUOdKBNpXAOcUIE",
  //   authDomain: "sdffd-f75d4.firebaseapp.com",
  //   projectId: "sdffd-f75d4",
  //   storageBucket: "sdffd-f75d4.firebasestorage.app",
  //   messagingSenderId: "13087329693",
  //   appId: "1:13087329693:web:304a0d3f0c7abb1bf67374",
  //   measurementId: "G-TSQE77XQ7Q"
  // };

  // const app = initializeApp(firebaseConfig);
  // const analytics = getAnalytics(app);

  return (
    
    <div>
      <Header/>
    <Router>

      <Routes>
        <Route path="/" element={<HomePage />} /> {/* 기본 페이지 */}
        <Route path="/articles/:period" element={<ArticlesPage />} /> {/* 1, 7일 값에 맞는 페이지 */}
      </Routes>

      
      
    </Router>
    <Footer />
    </div>
  );
}

export default App;
