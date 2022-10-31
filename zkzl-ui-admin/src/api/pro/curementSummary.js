import request from '@/utils/request'

// 创建采购汇总
export function createcurementSummary(data) {
  return request({
    url: '/pro/curement-summary/create',
    method: 'post',
    data: data
  })
}

// 更新采购汇总
export function updatecurementSummary(data) {
  return request({
    url: '/pro/curement-summary/update',
    method: 'put',
    data: data
  })
}

// 删除采购汇总
export function deletecurementSummary(id) {
  return request({
    url: '/pro/curement-summary/delete?id=' + id,
    method: 'delete'
  })
}

// 获得采购汇总
export function getcurementSummary(id) {
  return request({
    url: '/pro/curement-summary/get?id=' + id,
    method: 'get'
  })
}

// 获得采购汇总分页
export function getcurementSummaryPage(query) {
  return request({
    url: '/pro/curement-summary/page',
    method: 'get',
    params: query
  })
}

// 导出采购汇总 Excel
export function exportcurementSummaryExcel(query) {
  return request({
    url: '/pro/curement-summary/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
