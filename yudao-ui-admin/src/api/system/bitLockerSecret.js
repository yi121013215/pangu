import request from '@/utils/request'

// 创建密钥管理
export function createBitLockerSecret(data) {
  return request({
    url: '/system/bit-locker-secret/create',
    method: 'post',
    data: data
  })
}

// 更新密钥管理
export function updateBitLockerSecret(data) {
  return request({
    url: '/system/bit-locker-secret/update',
    method: 'put',
    data: data
  })
}

// 删除密钥管理
export function deleteBitLockerSecret(id) {
  return request({
    url: '/system/bit-locker-secret/delete?id=' + id,
    method: 'delete'
  })
}

// 获得密钥管理
export function getBitLockerSecret(id) {
  return request({
    url: '/system/bit-locker-secret/get?id=' + id,
    method: 'get'
  })
}

// 获得密钥管理分页
export function getBitLockerSecretPage(query) {
  return request({
    url: '/system/bit-locker-secret/page',
    method: 'get',
    params: query
  })
}

// 导出密钥管理 Excel
export function exportBitLockerSecretExcel(query) {
  return request({
    url: '/system/bit-locker-secret/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
