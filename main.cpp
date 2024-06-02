#include <iostream>
#include <vector>
#include <cmath>
#include <random>
#include <stdexcept>
#include "src/Ward/Ward.h"

using namespace std;

// Hàm để tạo ra các số nguyên tuân theo phân phối chuẩn
vector<int> generateNormalDistribution(int total, int n) {
    random_device rd; //  Tạo số ngẫu nhiên
    mt19937 gen(rd()); // Khởi tạo bộ sinh số ngẫu nhiên Mersenne Twister
    normal_distribution<> d(total / n, 1); // Phân phối chuẩn với trung bình=total/n và độ lệch chuẩn=1

    vector<int> result; // Vector để lưu kết quả
    int sum = 0; // Biến để lưu tổng các phần tử

    // Tạo n số tuân theo phân phối chuẩn
    while (result.size() < n) {
        int number = round(d(gen)); // Tạo số
        if (number > 0) { // Đảm bảo số là dương
            result.push_back(number); // Thêm số vào vector kết quả
            sum += number; // Cộng số vào tổng
        }
    }

    // Điều chỉnh các số để đảm bảo tổng chính xác
    int diff = total - sum;
    for (int i = 0; i < abs(diff); ++i) {
        result[i % n] += (diff > 0) ? 1 : -1; // Điều chỉnh kết quả bằng cách cộng hoặc trừ 1
    }

    return result; // Trả về vector kết quả
}

// Hàm để phân phối giá trị cho các Ward
vector<pair<Ward, int>> distributeWardValues(vector<Ward>& wards, int triple, int single, int numOfAgents) {
    // Kiểm tra xem tổng của triple và single có bằng numOfAgents không
    if (triple + single != numOfAgents) {
        throw invalid_argument("The sum of triple and single must equal numOfAgents.");
    }

    int total = triple * 3 + single; // Tính tổng giá trị cần phân phối
    int n = wards.size(); // Lấy số lượng các Ward

    // Tạo ra một phân phối chuẩn của tổng giá trị
    vector<int> distribution = generateNormalDistribution(total, n);

    vector<pair<Ward, int>> result; // Vector để lưu kết quả
    for (int i = 0; i < n; ++i) {
        // Tạo một cặp của Ward và giá trị phân phối, và thêm nó vào vector kết quả
        result.push_back(make_pair(wards[i], distribution[i]));
    }

    return result; // Trả về vector kết quả
}