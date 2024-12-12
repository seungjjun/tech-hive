import React, { useEffect, useState } from 'react';
import { NavLink, useLocation } from 'react-router-dom';
import {fetchSearchTechArticles} from "../services/SearchService";

import InfiniteScroll from '../common/InfiniteScroll';
import companyLogos from "../common/CompanyLogos";
import DateTimeConverter from "../common/DateTimeConverter";

import '../styles/searchResults.css';

const SearchResults = () => {
    const location = useLocation();
    const queryParams = new URLSearchParams(location.search);
    const searchTerm = queryParams.get('q');

    const [searchTechArticles, setSearchTechArticles] = useState([]);
    const [page, setPage] = useState(1);
    const [limit] = useState(4);
    const [hasMore, setHasMore] = useState(true);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {
        setSearchTechArticles([]);
        setPage(1);
        setHasMore(true);
    }, [searchTerm]);

    useEffect(() => {
        const getSearchTechArticles = async () => {
            setLoading(true);
            setError(null);
            try {
                const data = await fetchSearchTechArticles(searchTerm, page, limit);
                setSearchTechArticles(prevArticles => [...prevArticles, ...data.techArticleResponseList]);
                if (data.techArticleResponseList.length < limit) {
                    setHasMore(false);
                }
            } catch (error) {
                setError(error.message);
            } finally {
                setLoading(false);
            }
        };

        if (searchTerm) {
            getSearchTechArticles();
        }
    }, [searchTerm, page, limit]);

    const loadMore = () => {
        setPage(prevPage => prevPage + 1);
    };

    return (
        <div className="search-box">
            <div className="search-result-box">
                <div className="search-result-count-text">
                    <span className="search-count-text">{searchTechArticles.length}개</span>
                    <span>의 검색결과</span>
                </div>
                <InfiniteScroll loading={loading}
                                hasMore={hasMore}
                                onLoadMore={loadMore}
                                className={"search-result-tech-article-box"}>
                    {searchTechArticles.map((article, index) => (
                        <NavLink
                            to={`/articles/${article.id}`}
                            key={article.id}
                            className="search-article-card"
                        >
                            <div className="article-card-logo-box">
                                <div className="article-card-logo-img-box">
                                    <img src={companyLogos[article.companyName]}
                                         alt={`${article.companyName || 'Unknown'} Logo`}/>
                                </div>
                                <span className="article-card-logo-company-name">{article.companyName}</span>
                            </div>
                            <div className="article-card-thumbnail-box">
                                <img src={article.imageUrl}
                                     alt={`${article.companyName || 'Unknown'} article thumbnail image`}/>
                            </div>
                            <div className="article-card-article-content-box">
                                <span className="article-card-article-title">{article.title}</span>
                                <p className="article-card-article-summary">{article.oneLineSummary}</p>
                            </div>
                            <div className="article-card-category-box">
                                {article.categoryNames.map((category) => (
                                    <div className="article-card-category-button">
                                        <span
                                            className="article-card-category-button-text">{category}</span>
                                    </div>
                                ))}
                            </div>
                            <div className="article-card-article-meta-box">
                                <span>{DateTimeConverter.formatToKoreanDate(article.publishedDate)}</span>
                                <span>·</span>
                                <span>{article.viewCount}회</span>
                            </div>
                        </NavLink>
                    ))}
                </InfiniteScroll>
                {loading && <div className="loading">로딩 중...</div>}
                {error && <div className="error">에러: {error}</div>}
            </div>
        </div>
    );
};

export default SearchResults;
