import React, { useRef, useCallback } from 'react';

    const InfiniteScroll = ({ loading, hasMore, onLoadMore, children, className }) => {
    const observer = useRef();

    const lastElementRef = useCallback(
        node => {
            if (loading) return;
            if (observer.current) observer.current.disconnect();
            observer.current = new IntersectionObserver(entries => {
                if (entries[0].isIntersecting && hasMore) {
                    onLoadMore();
                }
            });
            if (node) observer.current.observe(node);
        },
        [loading, hasMore, onLoadMore]
    );

    return (
        <div className={className}>
            {React.Children.map(children, (child, index) => {
                if (React.Children.count(children) === index + 1) {
                    return React.cloneElement(child, {ref: lastElementRef});
                }
                return child;
            })}
        </div>
    );
    };

export default InfiniteScroll;
