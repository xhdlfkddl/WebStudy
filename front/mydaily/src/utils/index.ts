export const getPageCount = (list: any[], count: number) => 
    Math.floor((list.length - 1) / count) + 1;

export const getExpireTime = (expiredTime: number) => {
    const now = new Date().getTime();
    const tokenExpriedTime = new Date(now + expiredTime);
    
    return tokenExpriedTime;
}

