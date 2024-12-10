import React, { useEffect, useState } from 'react';
import { NavLink, useSearchParams } from 'react-router-dom';
import {fetchTechArticles} from "../services/TechArticleService";

import InfiniteScroll from '../common/InfiniteScroll';

import ArticleCard from "./ArticleCard";
import '../styles/techArticleList.css';

const TechArticleList = () => {
    const [searchParams] = useSearchParams();
    const sortOrder = (searchParams.get('sort') || 'recent').toUpperCase();

    const [articles, setArticles] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [hasMore, setHasMore] = useState(true);
    const [page, setPage] = useState(1);
    const [limit] = useState(12);

    useEffect(() => {
        setPage(1);
        setHasMore(true);
        setArticles([]);
    }, [sortOrder]);

    useEffect(() => {
        const getArticles = async () => {
            setLoading(true);
            setError(null);
            try {
                const data = await fetchTechArticles(page, limit, sortOrder);
                setArticles(prevArticles => [...prevArticles, ...data]);
                if (data.length < limit) {
                    setHasMore(false);
                }
            } catch (error) {
                setError(error.message);
            } finally {
                setLoading(false);
            }
        };
        getArticles();
    }, [page, limit, sortOrder]);

    const loadMore = () => {
        setPage(prevPage => prevPage + 1);
    };

    return (
        <div className="tech-article-box">
            <div className="tech-article-header-box">
                <h2 className="tech-article-header-title">전체 글</h2>
            </div>
            <div className="tech-article-order-button-box">
                <NavLink
                    to={`?sort=recent`}
                    className={() =>
                        sortOrder === 'RECENT'
                            ? "tech-article-recent-order-button active"
                            : "tech-article-recent-order-button"
                    }
                >
                    <span className="tech-article-recent-order-button-text">최신 순</span>
                </NavLink>
                <NavLink
                    to={`?sort=popular`}
                    className={() =>
                        sortOrder === 'POPULAR'
                            ? "tech-article-popular-order-button active"
                            : "tech-article-popular-order-button"
                    }
                >
                    <span className="tech-article-popular-order-button-text">인기 순</span>
                </NavLink>
            </div>
            <InfiniteScroll loading={loading}
                            hasMore={hasMore}
                            onLoadMore={loadMore}
                            className={"tech-article-list-box"}>
                {articles.map((article, index) => (
                    <ArticleCard
                        key={article.id}
                        id={article.id}
                        title={article.title}
                        author={article.companyName}
                        views={article.viewCount}
                        date={new Date(article.publishedDate).toLocaleDateString()}
                        oneLineSummary={article.oneLineSummary}
                        threeLineSummary={JSON.parse(article.threeLineSummary)}
                        coreSummary={JSON.parse(article.coreSummary)}
                        link={article.link}
                        thumbnailSrc={article.imageUrl}
                    />
                ))}
            </InfiniteScroll>
            {loading && <div className="loading">로딩 중...</div>}
            {error && <div className="error">에러: {error}</div>}
        </div>
    );
};

export default TechArticleList;