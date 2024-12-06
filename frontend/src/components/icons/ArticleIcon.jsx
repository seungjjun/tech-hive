import React from 'react';

const ArticleIcon = ({ className }) => (
    <svg
        className={className}
        width="20"
        height="20"
        viewBox="0 0 20 20"
        fill="none"
        xmlns="http://www.w3.org/2000/svg"
    >
        <g clipPath="url(#clip0_146_1001)">
            <path
                d="M10.0003 1.6665L1.66699 5.83317L10.0003 9.99984L18.3337 5.83317L10.0003 1.6665Z"
                stroke="currentColor"
                strokeWidth="1.6"
                strokeLinecap="round"
                strokeLinejoin="round"
            />
            <path
                d="M1.66699 14.1665L10.0003 18.3332L18.3337 14.1665"
                stroke="currentColor"
                strokeWidth="1.6"
                strokeLinecap="round"
                strokeLinejoin="round"
            />
            <path
                d="M1.66699 10L10.0003 14.1667L18.3337 10"
                stroke="currentColor"
                strokeWidth="1.6"
                strokeLinecap="round"
                strokeLinejoin="round"
            />
        </g>
        <defs>
            <clipPath id="clip0_146_1001">
                <rect width="20" height="20" fill="white"/>
            </clipPath>
        </defs>
    </svg>
);

export default ArticleIcon;