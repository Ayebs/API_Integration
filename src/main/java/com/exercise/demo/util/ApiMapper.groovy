package com.exercise.demo.util

import com.exercise.demo.model.ExternalApiResponse
import com.exercise.demo.model.StudentResponse

class ApiMapper {

    static StudentResponse toStudentResponse(ExternalApiResponse externalApiResponse) {
        def response = new StudentResponse()
        if (externalApiResponse.status) {
            response.success = true
            response.message = "Details retrieved successfully!"

            def studentDetails = new StudentResponse.StudentDetails()
            studentDetails.studentId = externalApiResponse.data.billReference
            studentDetails.studentName = externalApiResponse.data.customerName
            studentDetails.gender = externalApiResponse.data.details.gender
            studentDetails.className = externalApiResponse.data.customerSegment

            response.studentDetails = studentDetails
        } else {
            response.success = false
            response.message = "Failed to retrieve details."
        }
        return response
    }

}
