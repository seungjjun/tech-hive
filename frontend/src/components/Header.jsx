import React, { useState } from 'react';
import { NavLink, useNavigate } from 'react-router-dom';
import SearchIcon from "./icons/SearchIcon";
import '../styles/header.css';

const Header = () => {
    const [searchTerm, setSearchTerm] = useState('');
    const navigate = useNavigate();

    const handleInputChange = (e) => {
        setSearchTerm(e.target.value);
    };

    const handleSearch = (e) => {
        e.preventDefault();
        const trimmedTerm = searchTerm.trim();
        if (trimmedTerm) {
            navigate(`/search?q=${encodeURIComponent(trimmedTerm)}`);
            setSearchTerm('');
        }
    };

    return (
        <div className="home-header">
            <NavLink
                to="/"
                className="header-logo">
                <img className="tech-hive-logo" src="/assets/img/techhive-logo.png" alt="TechHive Logo"/>
                <p className="text-tech-hive">
                    <span className="tech-title">Tech</span>
                    <span className="hive-title">Hive</span>
                </p>
            </NavLink>
            <form className="search-bar" onSubmit={handleSearch}>
                <input
                    type="text"
                    className="search-text-input"
                    placeholder="다양한 기술을 검색해 보세요"
                    value={searchTerm}
                    onChange={handleInputChange}
                />
                <button type="submit" className="search-icon-button">
                    <SearchIcon className="search-icon"/>
                </button>
            </form>
        </div>
    );
};

export default Header;