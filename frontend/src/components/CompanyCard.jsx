import React, { forwardRef } from 'react';
import { NavLink } from 'react-router-dom';

import "../styles/companyCard.css"
import DateTimeConverter from "../common/DateTimeConverter";

const CompanyCard = forwardRef((props, ref) => {
    const {
        id,
        name,
        articleCount,
        articleLatestPublishedDate,
        logo
    } = props;

    const latestDateMessage = articleLatestPublishedDate
        ? DateTimeConverter.formatToDate(articleLatestPublishedDate)
        : '최근 등록된 게시글이 없습니다';

    return (
        <NavLink to={`/companies/${id}`} className="company-card">
            <div className="company-card-logo-box">
                <img className="company-card-logo" src={logo} alt={`${name} logo`}/>
            </div>
            <div className="company-card-name-box">
                <span className="company-card-company-name">{name}</span>
            </div>
            <div className="company-card-meta-bax">
                <span className="company-card-article-count">게시글 {articleCount}개</span>
                <span className="company-card-latest-published-date">최근 등록 {latestDateMessage}</span>
            </div>
        </NavLink>
    )
});

export default CompanyCard;