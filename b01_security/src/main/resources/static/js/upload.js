/**
 * 첨부파일을 서버에 업로드 POST 요청
 * @param formObj
 * @returns {Promise<*>}
 */
async function uploadToServer(formObj) {
    console.log("upload to server...")
    console.log(formObj)

    const response = await axios({
        method: 'post',
        url: '/upload',
        data: formObj,
        headers: {
            'Content-Type': 'multipart/form-data',
        },
    });
    return response.data
}

/**
 *  첨부 파일을 서버에 삭제 요청
 * @param uuid
 * @param fileName
 * @returns {Promise<*>}
 */
async function removeFileToServer (uuid, fileName) {
    const response = await axios.delete(`/remove/${uuid}_${fileName}`)
    return response.data
}