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

    const initialVisibleCount = 5; // 초기에 보여줄 기업 개수 설정
    const [visibleCount, setVisibleCount] = useState(initialVisibleCount);
    const [isExpanded, setIsExpanded] = useState(false);

    const handleToggle = () => {
        if (isExpanded) {
            setVisibleCount(initialVisibleCount);
        } else {
            setVisibleCount(companies.length);
        }
        setIsExpanded(!isExpanded);
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
                    {companies.slice(0, visibleCount).map((company) => (
                        <NavLink
                            to={`/companies/${company.id}`}
                            key={company.id} // 고유하고 안정적인 키 사용
                            className="company-list-wrapper"
                        >
                            <div className={`company-box ${company.isUpdated ? 'updated' : 'not-updated'}`}>
                                <img
                                    src={companyLogos[company.name] || companyLogos.default}
                                    alt={`${company.name || 'Unknown'} Logo`}
                                    onError={(e) => {
                                        e.target.src = companyLogos.default;
                                    }}
                                />
                            </div>
                            <span className={`company-name ${company.isUpdated ? 'updated' : 'not-updated'}`}>
                            {company.name || 'Tech Hive'}
                        </span>
                        </NavLink>
                    ))}
                </div>
                {companies.length > initialVisibleCount && (
                    <button onClick={handleToggle} className="expand-button">
                        <span>{isExpanded ? '접기' : '펼치기'}</span>
                        <DirectionBottomIcon
                            className={`direction-bottom-icon ${isExpanded ? 'rotated' : ''}`}
                        />
                    </button>
                )}
            </div>
        </div>
    );
};

export default Sidebar;