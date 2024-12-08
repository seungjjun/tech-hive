class DateTimeConverter {
    /**
     * ISO 형식의 날짜 문자열을 "YYYY년M월D일" 형식으로 변환합니다.
     * @param {string} localDateTime - ISO 형식의 날짜 문자열 (예: "2022-12-07T11:55:13.478")
     * @returns {string} - 변환된 날짜 문자열 (예: "2022년12월7일")
     */
    static formatToKoreanDate(localDateTime) {
        if (!localDateTime) {
            throw new Error("유효한 날짜 문자열을 입력해주세요.");
        }

        const date = new Date(localDateTime);

        if (isNaN(date)) {
            throw new Error("유효한 날짜 형식이 아닙니다.");
        }

        const year = date.getFullYear();
        const month = date.getMonth() + 1;
        const day = date.getDate();

        return `${year}년${month}월${day}일`;
    }

    static formatToDate(localDateTime) {
        if (!localDateTime) {
            throw new Error("유효한 날짜 문자열을 입력해주세요.");
        }
        const date = new Date(localDateTime);
        if (isNaN(date)) {
            throw new Error("유효한 날짜 형식이 아닙니다.");
        }
        const year = date.getFullYear();
        const month = date.getMonth() + 1;
        const day = date.getDate();
        return `${year}.${month}.${day}`;
    }
}

export default DateTimeConverter;