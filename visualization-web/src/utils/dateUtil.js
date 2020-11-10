import moment from 'moment'

// --------------------------------------日维度---------------------------------------
/**
 * 昨天
 * @type {string}
 */
const yesterday = moment().subtract(1, 'days').format('YYYY-MM-DD')

/**
 * 前天
 * @type {string}
 */
const beforeYesterday = moment().subtract(1, 'days').format('YYYY-MM-DD')

/**
 * 获取自定义日期
 * @param params 整数, 正则为加, 负则为减
 * @returns {string}
 */
const getDate = function (params) {
  const date = params >= 0 ? moment().add(params, 'days').format('YYYY-MM-DD')
                           : moment().subtract(Math.abs(params), 'days').format('YYYY-MM-DD')
  return date
}

// --------------------------------------月维度---------------------------------------
/**
 * 当月
 * @type {string}
 */
const sameMonth = moment().format('YYYY-MM')

// --------------------------------------年维度---------------------------------------
/**
 * 今年
 */
const sameYear = moment().format('YYYY')

export { yesterday, beforeYesterday, getDate, sameMonth, sameYear }
