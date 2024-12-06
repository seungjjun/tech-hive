import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Main from './components/Main';
import Header from "./components/Header";
import Sidebar from "./components/Sidebar";
import TechArticleList from "./components/TechArticleList";
import CompanyList from "./components/CompanyList";
import SearchResults from "./components/SearchResults";
import './App.css'

function App() {
    return (
        <Router>
            <div className="App">
                <Header />
                <div className="body-container">
                    <Sidebar />
                    <Routes>
                        <Route path="/" element={<Main />} />
                        <Route path="/articles/:sort?" element={<TechArticleList />} />
                        <Route path="/companies/:sort?" element={<CompanyList />} />
                        <Route path="/category" element={<div></div>} />
                        <Route path="/search" element={<SearchResults />} />
                    </Routes>
                </div>
            </div>
        </Router>
    );
}

export default App;
