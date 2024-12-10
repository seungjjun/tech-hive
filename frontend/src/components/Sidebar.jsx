import React, {useEffect, useState} from 'react';
import {NavLink} from 'react-router-dom';
import '../styles/sidebar.css';
import {fetchCompanies} from "../services/CompanyService";
import ArticleIcon from "./icons/ArticleIcon";
import CompanyIcon from "./icons/CompanyIcon";
import MainIcon from "./icons/MainIcon";

import DirectionBottomIcon from "./icons/DirectionBottomIcon";
import companyLogos from "../common/CompanyLogos";

const Sidebar = () => {
    const [companies, setCompanies] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [visibleCount, setVisibleCount] = useState(5);

    const handleExpand = () => {
        setVisibleCount((prevCount) => Math.min(prevCount + 5, companies.length));
    };

    const selections = [
        {id: 1, icon: MainIcon, label: '메인', path: '/'},
        {id: 2, icon: ArticleIcon, label: '전체 글', path: '/articles'},
        {id: 3, icon: CompanyIcon, label: '기업 별', path: '/companies'},
    ];

    useEffect(() => {
        const getCompanies = async () => {
            try {
                const data = await fetchCompanies('', '', 10);
                setCompanies(data);
            } catch (error) {
                console.error(error);
                setError(error.message);
            } finally {
                setLoading(false);
            }
        };
        getCompanies();
    }, []);

    if (loading) {
        return <div>Loading articles...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <div className="side-bar">
            <nav>
                {selections.map((item) => {
                    const IconComponent = item.icon;
                    return (
                        <NavLink
                            key={item.id}
                            to={item.path}
                            end={item.path === '/'}
                            className={({isActive}) => `side-selection ${isActive ? 'active' : ''}`}
                        >
                            <IconComponent className="icon"/>
                            <div className="selection-text">
                                <p>{item.label}</p>
                            </div>
                        </NavLink>
                    );
                })}
            </nav>
            <hr className="side-bar-line"></hr>
            <p className="side-bar-company-title">새로 올라온</p>
            <div className="side-bar-company-list-box">
                <div>
                    {companies.slice(0, visibleCount).map((company, index) => (
                        <NavLink
                            to={`/companies/${company.id}`}
                            key={`${company.id}-${Date.now()}`}
                            className="company-list-wrapper"
                        >
                            <div
                                className={`company-box ${company.isUpdated ? 'updated' : 'not-updated'}`}
                            >
                                <img
                                    src={companyLogos[company.name] || companyLogos.default}
                                    alt={`${company.name || 'Unknown'} Logo`}
                                />
                            </div>
                            <span className={`company-name ${company.isUpdated ? 'updated' : 'not-updated'}`}>{company.name || 'Tech Hive'}</span>
                        </NavLink>
                    ))}
                </div>
                {visibleCount < companies.length && (
                    <button onClick={handleExpand} className="expand-button">
                        <span>펼치기</span>
                        <DirectionBottomIcon className="direction-bottom-icon"/>
                    </button>
                )}
            </div>
        </div>
    );
};

export default Sidebar;