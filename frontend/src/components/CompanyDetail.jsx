import React, { useEffect, useState } from 'react';
import { useParams, useSearchParams, NavLink } from 'react-router-dom';

import companyLogos from "../common/CompanyLogos";
import "../styles/companyDetail.css"

import {fetchCompany} from "../services/CompanyService";
import {fetchTechArticlesByCompany} from "../services/TechArticleService";
import InfiniteScroll from "../common/InfiniteScroll";
import ArticleCard from "./ArticleCard";

const CompanyDetail = () => {
    const { id } = useParams();
    const [searchParams] = useSearchParams();
    const sort = (searchParams.get('sort') || 'recent').toUpperCase();

    const [company, setCompany] = useState([]);
    const [articles, setArticles] = useState([]);

    const [hasMore, setHasMore] = useState(true);
    const [page, setPage] = useState(1);
    const [limit] = useState(12);

    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        setPage(1);
        setHasMore(true);
        setArticles([]);
    }, [sort]);

    useEffect(() => {
        const getCompany = async () => {
            setLoading(true);
            setError(null);
            try {
                const data = await fetchCompany(id);
                setCompany(data);
            } catch (error) {
                setError(error.message);
            } finally {
                setLoading(false);
            }
        };
        getCompany();
    }, [id]);

    useEffect(() => {
        const getArticles = async () => {
            setLoading(true);
            setError(null);
            try {
                const data = await fetchTechArticlesByCompany(id, sort, page, limit);
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
    }, [id, page, limit, sort]);

    const loadMore = () => {
        setPage(prevPage => prevPage + 1);
    };

    return (
        <div className="company-detail-box">
            <div className="company-detail-header-box">
                <div className="company-detail-img-box">
                    <img className="company-detail-company-logo-img"
                         src={companyLogos[company.name]}
                         alt={`${company.name} img`}/>
                </div>
                <div className="company-detail-meta-box">
                    <span className="company-detail-company-name">{company.name}</span>
                    <span className="company-detail-article-count">게시글 {company.articleNumber}개</span>
                </div>
            </div>
            <div className="company-detail-order-button-box">
                <NavLink
                    to={`?sort=recent`}
                    className={() =>
                        sort === 'RECENT'
                            ? "company-detail-recent-order-button active"
                            : "company-detail-recent-order-button"
                    }
                >
                    <span className="company-detail-recent-order-button-text">최신 순</span>
                </NavLink>
                <NavLink
                    to={`?sort=popular`}
                    className={() =>
                        sort === 'POPULAR'
                            ? "company-detail-count-popular-button active"
                            : "company-detail-count-popular-button"
                    }
                >
                    <span className="company-detail-count-popular-button-text">인기 순</span>
                </NavLink>
            </div>
            <InfiniteScroll loading={loading}
                            hasMore={hasMore}
                            onLoadMore={loadMore}
                            className={"company-detail-article-list-box"}>
                {articles.map((article, index) => (
                    <ArticleCard
                        key={article.id}
                        id={article.id}
                        title={article.title}
                        author={article.companyName}
                        views={article.viewCount}
                        logoSrc={`/assets/img/${article.companyName.toLowerCase()}-logo.png`}
                        date={new Date(article.publishedDate).toLocaleDateString()}
                        oneLineSummary={article.oneLineSummary}
                        threeLineSummary={JSON.parse(article.threeLineSummary)}
                        coreSummary={JSON.parse(article.coreSummary)}
                        link={article.link}
                        thumbnailSrc={article.imageUrl}
                    />
                ))}
            </InfiniteScroll>
        </div>
    );
};


export default CompanyDetail;