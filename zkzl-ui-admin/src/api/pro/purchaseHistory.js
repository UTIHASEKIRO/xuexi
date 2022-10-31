import request from '@/utils/request'

// 创建供货商供应记录
export function createPurchaseHistory(data) {
  return request({
    url: '/pro/purchase-history/create',
    method: 'post',
    data: data
  })
}

// 更新供货商供应记录
export function updatePurchaseHistory(data) {
  return request({
    url: '/pro/purchase-history/update',
    method: 'put',
    data: data
  })
}

// 删除供货商供应记录
export function deletePurchaseHistory(id) {
  return request({
    url: '/pro/purchase-history/delete?id=' + id,
    method: 'delete'
  })
}

// 获得供货商供应记录
export function getPurchaseHistory(id) {
  return request({
    url: '/pro/purchase-history/get?id=' + id,
    method: 'get'
  })
}

// 获得供货商供应记录分页
export function getPurchaseHistoryPage(query) {
  return request({
    url: '/pro/purchase-history/page',
    method: 'get',
    params: query
  })
}

// 导出供货商供应记录 Excel
export function exportPurchaseHistoryExcel(query) {
  return request({
    url: '/pro/purchase-history/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
