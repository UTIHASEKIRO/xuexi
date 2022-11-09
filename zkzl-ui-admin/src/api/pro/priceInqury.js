import request from '@/utils/request'

// 创建询价
export function createPriceInqury(data) {
  return request({
    url: '/pro/price-inqury/create',
    method: 'post',
    data: data
  })
}

// 更新询价
export function updatePriceInqury(data) {
  return request({
    url: '/pro/price-inqury/update',
    method: 'put',
    data: data
  })
}

// 删除询价
export function deletePriceInqury(id) {
  return request({
    url: '/pro/price-inqury/delete?id=' + id,
    method: 'delete'
  })
}

// 获得询价
export function getPriceInqury(query) {
  return request({
    url: '/pro/price-inqury/get',
    method: 'get',
    params: query
  })
}

// 获得询价分页
export function getPriceInquryPage(query) {
  return request({
    url: '/pro/price-inqury/page',
    method: 'get',
    params: query
  })
}

// 导出询价 Excel
export function exportPriceInquryExcel(query) {
  return request({
    url: '/pro/price-inqury/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}

export function pageManage(query) {
  return request({
    url: '/pro/price-inqury/page-manage',
    method: 'get',
    params: query,
  })
}

export function pageCommon(query) {
  return request({
    url: '/pro/price-inqury/page-common',
    method: 'get',
    params: query,
  })
}


//确定

export function update(query) {
  return request({
    url: '/pro/price-inqury/update',
    method: 'put',
    data: query,
  })
}
