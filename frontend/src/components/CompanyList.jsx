import React, { useEffect, useState } from 'react';
import { NavLink, useParams } from 'react-router-dom';

import "../styles/companyList.css"
import InfiniteScroll from "../common/InfiniteScroll";
import CompanyCard from "./CompanyCard";
import {fetchCompanies} from "../services/CompanyService";

const CompanyList = () => {
    const { sort } = useParams();
    const sortOrder = sort ? sort.toUpperCase() : 'RECENT';

    const [companies, setCompanies] = useState([]);

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [hasMore, setHasMore] = useState(true);
    const [page, setPage] = useState(1);
    const [limit] = useState(9);

    useEffect(() => {
        setPage(1);
        setHasMore(true);
        setCompanies([]);
    }, [sortOrder]);

    useEffect(() => {
        const getCompanies = async () => {
            setLoading(true);
            setError(null);
            try {
                const data = await fetchCompanies(sortOrder, page, limit);
                setCompanies(prevCompanies => [...prevCompanies, ...data]);
                if (data.length < limit) {
                    setHasMore(false);
                }
            } catch (error) {
                setError(error.message);
            } finally {
                setLoading(false);
            }
        };
        getCompanies();
    }, [page, limit, sortOrder]);

    const loadMore = () => {
        setPage(prevPage => prevPage + 1);
    };

    return (
        <div className="company-list-box">
            <div className="company-list-header-box">
                <h2 className="company-list-header-title">기업별</h2>
            </div>
            <div className="company-list-order-button-box">
                <NavLink
                    to="/companies/recent"
                    className={({ isActive }) =>
                        isActive ? "company-list-recent-order-button active" : "company-list-recent-order-button"
                    }
                >
                    <span className="company-list-recent-order-button-text">최신 순</span>
                </NavLink>
                <NavLink
                    to="/companies/count"
                    className={({ isActive }) =>
                        isActive ? "company-list-count-order-button active" : "company-list-count-order-button"
                    }
                >
                    <span className="company-list-count-order-button-text">게시글 순</span>
                </NavLink>
            </div>
            <InfiniteScroll loading={loading}
                            hasMore={hasMore}
                            onLoadMore={loadMore}
                            className={"company-list-grid-box"}>
                {companies.map((company, idnex) => (
                    <CompanyCard
                        key={company.id}
                        id={company.id}
                        name={company.name}
                        articleCount={company.techArticleCount}
                        articleLatestPublishedDate={company.latestPublishedDate}
                        logo={`/assets/icon/${company.name.toLowerCase()}-logo.svg`}
                    />
                ))}
            </InfiniteScroll>
        </div>
    );

};

export default CompanyList;