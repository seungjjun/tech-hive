import React, {useEffect, useState} from 'react';
import {NavLink, useParams} from 'react-router-dom';

import companyLogos from "../common/CompanyLogos";
import "../styles/techArticleDetail.css"
import {fetchRelatedTechArticlesByCompanyId, fetchTechArticleById} from "../services/TechArticleService";
import {fetchCompany} from "../services/CompanyService";

const TechArticleDetail = () => {
    const {id} = useParams();
    const [article, setArticle] = useState([]);
    const [company, setCompany] = useState([]);
    const [companyId, setCompanyId] = useState(null);
    const [threeLineSummary, setThreeLineSummary] = useState([]);
    const [relatedArticles, setRelatedArticles] = useState([]);

    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [relatedLoading, setRelatedLoading] = useState(false);
    const [relatedError, setRelatedError] = useState(null);

    useEffect(() => {
        const getArticle = async () => {
            try {
                const data = await fetchTechArticleById(id);
                setArticle(data);
                setCompanyId(data.companyId);
                if (data.threeLineSummary) {
                    try {
                        const parsedSummary = JSON.parse(data.threeLineSummary);
                        setThreeLineSummary(parsedSummary);
                    } catch (parseError) {
                        console.error('threeLineSummary 파싱 실패:', parseError);
                        setThreeLineSummary([]);
                    }
                }
            } catch (error) {
                setError(error.message);
            } finally {
                setLoading(false);
            }
        };
        getArticle();
    }, [id, companyId]);

    useEffect(() => {
        if (!companyId) return;

        const getCompany = async () => {
            try {
                const data = await fetchCompany(companyId);
                setCompany(data);
            } catch (err) {
                setError(error.message);
            } finally {
                setLoading(false);
            }
        };

        getCompany();
    }, [id, companyId]);

    useEffect(() => {
        if (!companyId) return;

        const getRelatedArticles = async () => {
            setRelatedLoading(true);
            setRelatedError(null);
            try {
                const data = await fetchRelatedTechArticlesByCompanyId(id, companyId);
                setRelatedArticles(data);
            } catch (err) {
                setRelatedError(err.message || '관련 기사를 불러오는 중 오류가 발생했습니다.');
            } finally {
                setRelatedLoading(false);
            }
        };

        getRelatedArticles();
    }, [id, companyId]);

    return (
        <div className="tech-article-detail-box">
            <div className="tech-article-detail-content-box">
                <div>
                    <span className="tech-article-detail-title">{article.title}</span>
                    <div className="tech-article-detail-meta-box">
                        <div className="tech-article-detail-logo-box">
                            <img className="tech-article-detail-logo-img"
                                 src={companyLogos[article.companyName]}
                                 alt={`${article.companyName} logo img`}
                            />
                        </div>
                        <div className="tech-article-detail-author-and-date-box">
                            <span className="tech-article-detail-company-name">{article.companyName}</span>
                            <div className="tech-article-detail-count-box">
                                <span className="tech-article-detail-author-article-count">작성글 {company.articleNumber}</span>
                                <span className="tech-article-detail-article-view-count">{article.viewCount}회</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="tech-article-detail-category-box">
                    {article.categoryNames && article.categoryNames.length > 0 ? (
                        article.categoryNames.map((category, index) => (
                            <span key={index} className="tech-article-detail-category-item">
                                {category}
                            </span>
                        ))
                    ) : (
                        <span>카테고리가 없습니다.</span>
                    )}
                </div>
                <div className="tech-article-detail-content">
                    <div className="tech-article-detail-pin-point-text-box">
                        <h3 className="tech-article-detail-pin-point">📍 Pin point</h3>
                        <p className="tech-article-detail-pin-point-text">{article.oneLineSummary}</p>
                    </div>
                    <div className="tech-article-detail-core-text-box">
                        <h3 className="tech-article-detail-core">📝 핵심 내용</h3>
                        <p className="tech-article-detail-core-text">{article.coreSummary}</p>
                    </div>
                    <div className="tech-article-detail-three-line-summary-text-box">
                        <h3 className="tech-article-detail-three-line-summary">🗒 세줄 요약</h3>
                        {threeLineSummary.length > 0 ? (
                            threeLineSummary.map((line, index) => (
                                <li key={index} className="tech-article-detail-three-line-summary-text">
                                    {line}
                                </li>
                            ))
                        ) : (
                            <p>세줄 요약 정보가 없습니다.</p>
                        )}
                    </div>
                    <NavLink to={article.ogWebUrl} className="tech-article-detail-og-tag-box">
                        <img className="tech-article-detail-og-tag-img"
                             src={article.ogImageUrl}
                             alt={`og img of ${article.ogTitle}`}
                        />
                        <div className="tech-article-detail-og-tag-meta-box">
                            <p className="tech-article-detail-og-tag-title">{article.ogTitle}</p>
                            <a className="tech-article-detail-og-tag-url">{article.ogWebUrl}</a>
                        </div>
                    </NavLink>
                </div>
            </div>
            <div className="tech-article-detail-related-box">
                <span className="tech-article-detail-related-title">이 기업의 다른 아티클</span>
                <div
                    className="tech-article-detail-related-content-box"
                >
                    {!relatedLoading && !relatedError && relatedArticles.length > 0 ? (
                        relatedArticles.map((related) => (
                            <NavLink
                                className="tech-article-detail-related-nav"
                                key={related.relatedArticleId}
                                to={`/articles/${related.relatedArticleId}`}
                            >
                                <img className="tech-article-detail-related-content-img"
                                     src={related.thumbnailImageUrl} />
                                <span className="tech-article-detail-related-content-title">{related.title}</span>
                            </NavLink>
                        ))
                    ) : (
                        <div>연관 아티클이 없습니다.</div>
                    )}
                </div>
            </div>
        </div>
    );
};
export default TechArticleDetail;