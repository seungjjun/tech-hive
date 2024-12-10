import React from 'react';
import { BrowserRouter as Router, Routes, Route, useMatch } from 'react-router-dom';
import Main from './components/Main';
import Header from "./components/Header";
import Sidebar from "./components/Sidebar";
import TechArticleList from "./components/TechArticleList";
import CompanyList from "./components/CompanyList";
import CompanyDetail from "./components/CompanyDetail";
import SearchResults from "./components/SearchResults";
import TechArticleDetail from "./components/TechArticleDetail";
import './App.css'

function BodyContainer() {
    const articleMatch = useMatch('/articles/:id');
    const searchMatch = useMatch('/search');
    const hideSidebar = !!articleMatch || !!searchMatch;

    return (
        <div className="body-container">
            {!hideSidebar && <Sidebar />}
            <Routes>
                <Route path="/" element={<Main />} />
                <Route path="/articles/:id" element={<TechArticleDetail />} />
                <Route path="/articles" element={<TechArticleList />} />
                <Route path="/companies/recent" element={<CompanyList />} />
                <Route path="/companies/count" element={<CompanyList />} />
                <Route path="/companies/:id" element={<CompanyDetail />} />
                <Route path="/companies" element={<CompanyList />} />
                <Route path="/search" element={<SearchResults />} />
            </Routes>
        </div>
    );
}

function App() {
    return (
        <Router>
            <div className="App">
                <Header />
                <BodyContainer />
            </div>
        </Router>
    );
}

export default App;
