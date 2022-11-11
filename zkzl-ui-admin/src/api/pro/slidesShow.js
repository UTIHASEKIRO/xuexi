import request from '@/utils/request'

// 创建轮播图
export function createSlidesShow(data) {
  return request({
    url: '/pro/slides-show/create',
    method: 'post',
    data: data
  })
}

// 更新轮播图
export function updateSlidesShow(data) {
  return request({
    url: '/pro/slides-show/update',
    method: 'put',
    data: data
  })
}

// 删除轮播图
export function deleteSlidesShow(id) {
  return request({
    url: '/pro/slides-show/delete?id=' + id,
    method: 'delete'
  })
}

// 获得轮播图
export function getSlidesShow(id) {
  return request({
    url: '/pro/slides-show/get?id=' + id,
    method: 'get'
  })
}

// 获得轮播图分页
export function getSlidesShowPage(query) {
  return request({
    url: '/pro/slides-show/page',
    method: 'get',
    params: query
  })
}

// 导出轮播图 Excel
export function exportSlidesShowExcel(query) {
  return request({
    url: '/pro/slides-show/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
