import request from '@/utils/request'

// 创建产品图片
export function createductPic(data) {
  return request({
    url: '/pro/duct-pic/create',
    method: 'post',
    data: data
  })
}

// 更新产品图片
export function updateductPic(data) {
  return request({
    url: '/pro/duct-pic/update',
    method: 'put',
    data: data
  })
}

// 删除产品图片
export function deleteductPic(id) {
  return request({
    url: '/pro/duct-pic/delete?id=' + id,
    method: 'delete'
  })
}

// 获得产品图片
export function getductPic(id) {
  return request({
    url: '/pro/duct-pic/get?id=' + id,
    method: 'get'
  })
}

// 获得产品图片分页
export function getductPicPage(query) {
  return request({
    url: '/pro/duct-pic/page',
    method: 'get',
    params: query
  })
}

// 导出产品图片 Excel
export function exportductPicExcel(query) {
  return request({
    url: '/pro/duct-pic/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
