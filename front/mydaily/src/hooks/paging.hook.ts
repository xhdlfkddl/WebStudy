import { useEffect, useState } from "react"
import { Comment, Product } from "src/interfaces"

const usePagingHook = (COUNT: number) => {
    const [ listItem, setListItem ] = useState<(Comment)[]>([]);
    const [ viewListItem, setViewListItem ] = useState<(Comment)[]>([]);
    const [ pageNumber, setPageNumber ] = useState<number>(1);

    const onPageHandler = (page: number) => {
        setPageNumber(page);

        const tmpList: (Comment)[] = [];

        const startIndex = COUNT * (page - 1);
        const endIndex = COUNT * page - 1;

        for(let i = startIndex; i <= endIndex; i++) {
            if (listItem.length < i + 1) break;
            tmpList.push(listItem[i]);
        }

        setViewListItem(tmpList);
    }

    useEffect(() => {
        onPageHandler(pageNumber);
    },[listItem])

    return { listItem, viewListItem, pageNumber, setListItem, onPageHandler, COUNT }
}

export default usePagingHook;