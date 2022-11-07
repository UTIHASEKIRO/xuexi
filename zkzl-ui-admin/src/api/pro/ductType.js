import request from '@/utils/request'

// 创建商品类别
export function createductType(data) {
  return request({
    url: '/pro/duct-type/create',
    method: 'post',
    data: data
  })
}

// 更新商品类别
export function updateductType(data) {
  return request({
    url: '/pro/duct-type/update',
    method: 'put',
    data: data
  })
}

// 删除商品类别
export function deleteductType(id) {
  return request({
    url: '/pro/duct-type/delete?id=' + id,
    method: 'delete'
  })
}

// 获得商品类别
export function getductType(id) {
  return request({
    url: '/pro/duct-type/get?id=' + id,
    method: 'get'
  })
}

// 获得商品类别分页
export function getductTypePage(query) {
  return request({
    url: '/pro/duct-type/page',
    method: 'get',
    params: query
  })
}

// 导出商品类别 Excel
export function exportductTypeExcel(query) {
  return request({
    url: '/pro/duct-type/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
export function ductTypeList(query) {
  return request({
    url: '/pro/duct-type/list',
    method: 'get',
    params: query,
  })
}
