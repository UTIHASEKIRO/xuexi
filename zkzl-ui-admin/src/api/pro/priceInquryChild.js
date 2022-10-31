import request from '@/utils/request'

// 创建询价表子
export function createPriceInquryChild(data) {
  return request({
    url: '/pro/price-inqury-child/create',
    method: 'post',
    data: data
  })
}

// 更新询价表子
export function updatePriceInquryChild(data) {
  return request({
    url: '/pro/price-inqury-child/update',
    method: 'put',
    data: data
  })
}

// 删除询价表子
export function deletePriceInquryChild(id) {
  return request({
    url: '/pro/price-inqury-child/delete?id=' + id,
    method: 'delete'
  })
}

// 获得询价表子
export function getPriceInquryChild(id) {
  return request({
    url: '/pro/price-inqury-child/get?id=' + id,
    method: 'get'
  })
}

// 获得询价表子分页
export function getPriceInquryChildPage(query) {
  return request({
    url: '/pro/price-inqury-child/page',
    method: 'get',
    params: query
  })
}

// 导出询价表子 Excel
export function exportPriceInquryChildExcel(query) {
  return request({
    url: '/pro/price-inqury-child/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
