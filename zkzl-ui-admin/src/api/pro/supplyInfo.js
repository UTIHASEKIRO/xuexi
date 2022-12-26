import request from '@/utils/request'

// 创建供货商信息
export function createSupplyInfo(data) {
  return request({
    url: '/pro/supply-info/create',
    method: 'post',
    data: data
  })
}

// 更新供货商信息
export function updateSupplyInfo(data) {
  return request({
    url: '/pro/supply-info/update',
    method: 'put',
    data: data
  })
}

// 删除供货商信息
export function deleteSupplyInfo(id) {
  return request({
    url: '/pro/supply-info/delete?id=' + id,
    method: 'delete'
  })
}

// 获得供货商信息
export function getSupplyInfo(id) {
  return request({
    url: '/pro/supply-info/get?id=' + id,
    method: 'get'
  })
}

// 获得供货商信息分页
export function getSupplyInfoPage(query) {
  return request({
    url: '/pro/supply-info/page',
    method: 'get',
    params: query
  })
}

// 导出供货商信息 Excel
export function exportSupplyInfoExcel(query) {
  return request({
    url: '/pro/supply-info/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
// 根据商品名查询供应商
export function listSupplyByproductId(query) {
  return request({
    url: '/pro/supply-info/listByproductId',
    method: 'get',
    params: query
  })
}