<#-- hotel_response.ftl -->
<#setting number_format= "0.#####################">
<#setting boolean_format="true,false">
{
"id": "<#if resultsMngo??>${(resultsMngo.id)!null}<#else>null</#if>",
"razorPayOrderId": "<#if resultsMngo??>${(resultsMngo.razorPayOrderId)!null}<#else>null</#if>",
"razorPayOrderStatus": "<#if resultsMngo??>${(resultsMngo.razorPayOrderStatus)!null}<#else>null</#if>",
"razorPayOrderMessage": "<#if resultsMngo??>${(resultsMngo.razorPayOrderMessage)!null}<#else>null</#if>",
"userId": "<#if resultsMngo??>${(resultsMngo.userId)!null}<#else>null</#if>",
"user": null, <#-- this varibale nither present in Mngo or TBO -->

"Status": {
"Code": "${(resultsTbo.status.code)!200}",
"Description": "${(resultsTbo.status.description)!'Successful'}"
},

"HotelResult": [
<#if (resultsTbo.hotelResult)??>
    <#list resultsTbo.hotelResult as hotel>
        {
        "HotelCode": "${hotel.hotelCode!null}",
        "Currency": "${hotel.currency!null}",
        "Rooms": [
        <#if (hotel.rooms)??>
            <#list hotel.rooms as room>
                {
                "taxesAndOtherCharges": ${room.totalTax?default(0)},
                "totalAmount": ${room.totalFare?default(0)},
                "Name": [<#list room.name as name>"${name}"<#if name_has_next>,</#if></#list>],
                "BookingCode": "${room.bookingCode!null}",
                "Inclusion": "${room.inclusion!null}",
                "DayRates": [
                <#if (room.dayRates)??>
                    <#list room.dayRates as dayRateList>
                        [
                        <#list dayRateList as dayRate>
                            {
                            "BasePrice": "${dayRate.basePrice?number}"
                            }
                            <#if dayRate_has_next>,</#if>
                        </#list>
                        ]
                        <#if dayRateList_has_next>,</#if>
                    </#list>
                </#if>
                ],
                "TotalFare": ${room.totalFare?default(0)},
                "TotalTax": ${room.totalTax?default(0)},
                "NetAmount": ${room.totalFare?default(0)},
                "NetTax": ${room.totalTax?default(0)},
                "RoomPromotion": <#if room.roomPromotion??>[<#list room.roomPromotion as promo>"${promo}"<#if promo_has_next>,</#if></#list>]<#else>null</#if>,
                "CancelPolicies": [
                <#if (room.cancelPolicies)??>
                    <#list room.cancelPolicies as policy>
                        {
                        "Index": "${policy.index?default('')}",
                        "FromDate": "${policy.fromDate!null}",
                        "ChargeType": "${policy.chargeType!null}",
                        "CancellationCharge": "${policy.cancellationCharge?default(0)}"
                        }
                        <#if policy_has_next>,</#if>
                    </#list>
                </#if>
                ],
                "MealType": "${room.mealType!null}",
                "Supplements": <#if (room.supplements)??>${room.supplements?json_string}<#else>null</#if>,
                "IsRefundable": "${room.refundable?c}",
                "WithTransfers": "${room.withTransfers?c}",
                "amenities": <#if room.amenities??>[<#list room.amenities as amenity>"${amenity}"<#if amenity_has_next>,</#if></#list>]<#else>null</#if>,
                "LastCancelationDeadline": "${room.lastCancellationDeadline!''}",

                "PriceBreakUp": [
                <#if (room.priceBreakUps)??>
                    <#list room.priceBreakUps as priceBreakUp>
                        {
                        "RoomRate": ${priceBreakUp.roomRate?default(0)},
                        "RoomTax": ${priceBreakUp.roomTax?default(0)},
                        "AgentCommission": ${priceBreakUp.agentCommission?default(0)},
                        "TaxBreakup": [
                        <#if priceBreakUp.taxBreakup??>
                            <#list priceBreakUp.taxBreakup as tax>
                                {
                                "TaxType": "${tax.taxType}",
                                "TaxableAmount": ${tax.taxableAmount?default(0)},
                                "TaxPercentage": ${tax.taxPercentage?default(0)},
                                "TaxAmount": ${tax.taxAmount?default(0)}
                                }
                                <#if tax_has_next>,</#if>
                            </#list>
                        <#else>
                            null
                        </#if>
                        ]
                        }
                        <#if priceBreakUp_has_next>,</#if>
                    </#list>
                </#if>
                ]
                }
                <#if room_has_next>,</#if>
            </#list>
        </#if>
        ],
        "RateConditions": <#if hotel.rateConditions??>[<#list hotel.rateConditions as condition>"${condition}"<#if condition_has_next>,</#if></#list>]<#else>null</#if>
        }
        <#if hotel_has_next>,</#if>
    </#list>
</#if>
],

"ValidationInfo": <#if resultsTbo.validationInfo??>
    {
    "PanMandatory": ${resultsTbo.validationInfo.panMandatory?c},
    "PassportMandatory": ${resultsTbo.validationInfo.passportMandatory?c},
    "CorporateBookingAllowed": ${resultsTbo.validationInfo.corporateBookingAllowed?c},
    "PanCountRequired": "${resultsTbo.validationInfo.panCountRequired?default(0)}",
    "SamePaxNameAllowed": ${resultsTbo.validationInfo.samePaxNameAllowed?c},
    "SpaceAllowed": ${resultsTbo.validationInfo.spaceAllowed?c},
    "SpecialCharAllowed": ${resultsTbo.validationInfo.specialCharAllowed?c},
    "PaxNameMinLength": "${resultsTbo.validationInfo.paxNameMinLength?default(0)}",
    "PaxNameMaxLength": "${resultsTbo.validationInfo.paxNameMaxLength?default(50)}",
    "CharLimit": ${resultsTbo.validationInfo.charLimit?c},
    "PackageFare": ${resultsTbo.validationInfo.packageFare?c},
    "PackageDetailsMandatory": ${resultsTbo.validationInfo.packageDetailsMandatory?c},
    "DepartureDetailsMandatory": ${resultsTbo.validationInfo.departureDetailsMandatory?c},
    "GSTAllowed": ${resultsTbo.validationInfo.gstAllowed?c}
    }<#else>
    null
</#if>,

"BookResult": <#if resultsMngo?? && resultsMngo.bookResult??>
    {
    "VoucherStatus": "<#if resultsMngo.bookResult.voucherStatus??>${resultsMngo.bookResult.voucherStatus?c}<#else>null</#if>",
    "ResponseStatus": "${resultsMngo.bookResult.responseStatus!0}",
    "Error": <#if resultsMngo.bookResult.error??>
    {
    "ErrorCode": "${resultsMngo.bookResult.error.errorCode!0}",
    "ErrorMessage": "${resultsMngo.bookResult.error.errorMessage!null}"
    }
<#else>
    null
</#if>,
    "TraceId": "${resultsMngo.bookResult.traceId!null}",
    "Status": "${resultsMngo.bookResult.status!0}",
    "HotelBookingStatus": "${resultsMngo.bookResult.hotelBookingStatus!null}",
    "InvoiceNumber": "${resultsMngo.bookResult.invoiceNumber!null}",
    "ConfirmationNo": "${resultsMngo.bookResult.confirmationNo!null}",
    "BookingRefNo": "${resultsMngo.bookResult.bookingRefNo!null}",
    "BookingId": "${resultsMngo.bookResult.bookingId!null}",
    "IsPriceChanged": "<#if resultsMngo.bookResult.isPriceChanged??>${resultsMngo.bookResult.priceChanged?c}<#else>false</#if>",
    "IsCancellationPolicyChanged": "<#if resultsMngo.bookResult.isCancellationPolicyChanged??>${resultsMngo.bookResult.cancellationPolicyChanged?c}<#else>false</#if>",
    "FastaysId": "<#if resultsMngo.bookResult.fastaysId??>${resultsMngo.bookResult.fastaysId}<#else>null</#if>"

    }
<#else>
    null
</#if>

}
