async function get1(bno) {
    const result = await axios.get(`/replies/list/${bno}`)

    return result
}

// /**
//  * 댓글 처리
//  * @param bno
//  * @param page
//  * @param size
//  * @param goLast
//  * @returns {Promise<*>}
//  */
// async function getList({bno, page, size, goLast}) {
//     const result = await axios.get(`/replies/list/${bno}`, {params: {page, size}})
//     return result.data
// }

/**
 * 댓글 처리+댓글의 마지막 페이지(최근 댓글) 호출
 * @param bno
 * @param page
 * @param size
 * @param goLast
 * @returns {Promise<*>}
 */
async function getList({bno, page, size, goLast}) {
    const result = await axios.get(`/replies/list/${bno}`, {params: {page, size}})

    if (goLast) {
        const total = result.data.total
        const lastPage = parseInt(Math.ceil(total/size))

        return getList({bno:bno, page:lastPage, size:size})
    }

    return result.data
}

/**
 * 댓글 등록
 * @param replyObj
 * @returns {Promise<*>}
 */
async function addReply(replyObj) {
    const response = await axios.post(`/replies/`, replyObj)

    console.log(replyObj)
    return response.data
}

/**
 * 특정 번호의 댓글 조회
 * @param rno
 * @returns {Promise<*>}
 */
async function getReply(rno) {
    const response = await axios.get(`/replies/${rno}`)
    return response.data
}

/**
 * 특정 번호의 댓글 수정
 * @param replyObj
 * @returns {Promise<*>}
 */
async function modifyReply(replyObj) {
    const response = await axios.put(`/replies/${replyObj.rno}`, replyObj)
    return response.data
}

/**
 * 댓글 삭제
 * @param rno
 * @returns {Promise<*>}
 */
async function removeReply(rno) {
    const response = await axios.delete(`/replies/${rno}`)
    return response.data
}