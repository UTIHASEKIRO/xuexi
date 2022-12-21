import request from '@/utils/request'

// 创建新闻
export function createNews(data) {
  return request({
    url: '/pro/news/create',
    method: 'post',
    data: data
  })
}

// 更新新闻
export function updateNews(data) {
  return request({
    url: '/pro/news/update',
    method: 'put',
    data: data
  })
}

// 删除新闻
export function deleteNews(id) {
  return request({
    url: '/pro/news/delete?id=' + id,
    method: 'delete'
  })
}

// 获得新闻
export function getNews(id) {
  return request({
    url: '/pro/news/get?id=' + id,
    method: 'get'
  })
}

// 获得新闻分页
export function getNewsPage(query) {
  return request({
    url: '/pro/news/page',
    method: 'get',
    params: query
  })
}

// 导出新闻 Excel
export function exportNewsExcel(query) {
  return request({
    url: '/pro/news/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
