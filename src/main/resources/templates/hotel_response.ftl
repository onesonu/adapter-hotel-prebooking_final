<#setting number_format="0.##">
{
"Status": {
"Code": ${status.code},
"Description": "${status.description}"
},
"HotelResult": [
<#list hotelResult as hotel>
{
"HotelCode": "${hotel.hotelCode}",
"Currency": "${hotel.currency}",
"RateConditions": [
<#list hotel.rateConditions as condition>
"${condition}"<#if condition_has_next>,</#if>
</#list>
],
"Rooms": [
<#list hotel.rooms as room>
{
"Name": [
<#list room.name as roomName>
"${roomName}"<#if roomName_has_next>,</#if>
</#list>
],
"BookingCode": "${room.bookingCode}",
"Inclusion": "${room.inclusion}",
"DayRates": [
<#list room.dayRates as dayRateList>
[
<#list dayRateList as dayRate>
{
"BasePrice": ${dayRate.basePrice}
}<#if dayRate_has_next>,</#if>
</#list>
]<#if dayRateList_has_next>,</#if>
</#list>
],
"TotalFare": ${room.totalFare},
"TotalTax": ${room.totalTax},
"RoomPromotion": <#if room.roomPromotion??>[<#list room.roomPromotion as promo>"${promo}"<#if promo_has_next>,</#if></#list>]<#else>null</#if>,
"CancelPolicies": <#if room.cancelPolicies??>
[
<#list room.cancelPolicies as policy>
{
"Index": "${policy.index!""}",
"FromDate": "${policy.fromDate!""}",
"ChargeType": "${policy.chargeType!""}",
"CancellationCharge": ${policy.cancellationCharge!0.0}
}<#if policy_has_next>,</#if>
</#list>
]<#else>null</#if>
}<#if room_has_next>,</#if>
</#list>
]
}<#if hotel_has_next>,</#if>
</#list>
],
"ValidationInfo": {
"PanMandatory": ${validationInfo.panMandatory?c},
"PassportMandatory": ${validationInfo.passportMandatory?c}
}
}
