<template>
  <div class="app-example container">
    <!-- Create account -->
    <div class="row justify-content-center">
      <div class="col-md-7" data-target="toggle.grid">
        <div class="card border-bootstrap box-shadow mb-4">
          <div
            class="
              card-header
              text-bootstrap
              d-flex
              align-items-center
              justify-content-between
            "
          >
            <div class="col text-center">
              <strong>National Bank Of Krakozhia (Create Account)</strong>
            </div>
          </div>
          <div class="card-body">
            <form @submit.prevent class="needs-validation" novalidate="">
              <div class="alert alert-danger d-none">
                Please review the problems below:
              </div>

              <div class="form-floating mb-3">
                <input
                  v-model="customerId"
                  type="text"
                  class="form-control"
                  id="customerId"
                  placeholder="Customer Id"
                  required=""
                  style="
                    background-repeat: no-repeat;
                    background-size: 20px;
                    background-position: 97% center;
                    cursor: auto;
                  "
                />
                <label for="customerId">Customer Id</label>
                <div class="invalid-feedback">Customer ID can't be blank</div>
                <div class="valid-feedback">Looks good!</div>
              </div>

              <div class="form-floating mb-3">
                <select
                  class="form-select"
                  id="selectAccountTypes"
                  required=""
                  v-model="selectedAccountType"
                >
                  <option
                    v-for="(acc, key) in accountTypes"
                    :value="key"
                    :key="key"
                  >
                    {{ acc }}
                  </option>
                </select>
                <label for="selectAccountTypes">Account Type</label>
                <div class="invalid-feedback">
                  Please provide a valid value.
                </div>
                <div class="valid-feedback">Looks good!</div>
              </div>

              <div class="form-floating mb-3">
                <input
                  v-model="initialCredit"
                  type="text"
                  class="form-control"
                  id="inputAmount"
                  placeholder="Amount"
                  autocomplete="amount"
                  required=""
                />
                <label for="inputAmount">Amount</label>
                <div class="invalid-feedback">
                  Please provide a valid value.
                </div>
                <div class="valid-feedback">Looks good!</div>
              </div>
              <div class="col text-center">
                <button @click="createAccount()" class="btn btn-primary">
                  Create Account
                </button>
                <button @click="resetForm()" class="btn btn-primary">
                  Reset
                </button>
                <div class="acc-success">
                  <span v-show="isAccountCreated" class="badge alert-success"
                    >Account Created: {{ accountNumber }}</span
                  >
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
    <!-- View Details -->
    <div class="row justify-content-center">
      <div class="col-md-7" data-target="toggle.grid">
        <div class="card border-bootstrap box-shadow mb-4">
          <div
            class="
              card-header
              text-bootstrap
              d-flex
              align-items-center
              justify-content-between
            "
          >
            <div class="col text-center">
              <strong>National Bank Of Krakozhia (View Details)</strong>
            </div>
          </div>
          <div class="card-body">
            <form @submit.prevent class="needs-validation" novalidate="">
              <div class="alert alert-danger d-none">
                Please review the problems below:
              </div>
              <div class="row">
                <div class="col-sm-6">
                  <div class="form-floating mb-3">
                    <input
                      v-model="customerId"
                      type="text"
                      class="form-control"
                      id="customerId"
                      placeholder="Customer Id"
                      required=""
                      style="
                        background-repeat: no-repeat;
                        background-size: 20px;
                        background-position: 97% center;
                        cursor: auto;
                      "
                    />
                    <label for="customerId">Customer Id</label>
                    <div class="invalid-feedback">
                      Customer ID can't be blank
                    </div>
                    <div class="valid-feedback">Looks good!</div>
                  </div>
                </div>
                <div class="col-sm-6 text-center">
                  <button
                    @click="viewCustomerDetails()"
                    type="submit"
                    class="btn btn-primary"
                    id="viewdetails"
                  >
                    View Details
                  </button>
                </div>
                <!-- Customer Details -->
                <div v-if="customerDetails">
                  <div class="row">
                    <table
                      class="table table-striped table-hover table-condensed"
                    >
                      <thead>
                        <tr>
                          <th scope="col">Id</th>
                          <th scope="col">First Name</th>
                          <th scope="col">Last Name</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr>
                          <th scope="row">{{ customerDetails.id }}</th>
                          <td>{{ customerDetails.name }}</td>
                          <td>{{ customerDetails.surname }}</td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                  <!--Select Account to view transactions -->
                  <div class="form-floating mb-5">
                    <select
                      @change="onAccountSelect($event)"
                      class="form-select"
                      id="selectAccount"
                      required=""
                    >
                      <option
                        v-for="(acc, key) in customerDetails.accounts"
                        :value="key"
                        :key="key"
                      >
                        {{ acc.accountNumber }}
                      </option>
                    </select>
                    <label for="selectAccount">-Select Account-</label>
                    <div class="invalid-feedback">
                      Please provide a valid value.
                    </div>
                    <div class="valid-feedback">Looks good!</div>
                  </div>
                  <!-- Customer Account Transaction Details -->
                  <div v-if="transactions">
                  <div class="row">
                    <table
                      class="table table-striped table-hover table-condensed"
                    >
                      <thead>
                        <tr>
                          <th scope="col">Trans. Id</th>
                          <th scope="col">Trans. Type</th>
                          <th scope="col">Trans. Amount</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr
                          v-for="(transaction, key) in transactions"
                          :key="key"
                        >
                          <th scope="row">{{ transaction.transactionId }}</th>
                          <td>{{ transaction.transactionType }}</td>
                          <td>{{ transaction.transactionAmount }}</td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                  </div>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "App",
  data() {
    return {
      customerId: "",
      accountTypes: ["SAVINGS", "CURRENT"],
      selectedAccountType: "",
      selectedAccount: "",
      initialCredit: "",
      isAccountCreated: false,
      accountNumber: "",
      customerDetails: "",
      transactions: [],
    };
  },
  methods: {
    createAccount() {
      fetch("/api/account/create", {
        method: "post",
        headers: {
          Accept: "application/json, text/plain, */*",
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          customerId: this.customerId,
          initialCredit: this.initialCredit,
          accountType: this.accountTypes[this.selectedAccountType],
        }),
      })
        .then((response) => {
          if (response.ok) {
            this.isAccountCreated = true;
            return response.json();
          } else {
            throw new Error("Something went wrong");
          }
        })
        .then((account) => {
          this.accountNumber = account.accountNumber;
          console.log(account);
        })
        .catch((error) => {
          this.isAccountCreated = false;
          console.log(error);
        });
    },
    viewCustomerDetails() {
      fetch("/api/customer/" + this.customerId)
        .then((response) => {
          if (response.ok) {
            return response.json();
          } else {
            throw new Error("Something went wrong");
          }
        })
        .then((customerDetails) => {
          this.customerDetails = customerDetails;
          console.log(customerDetails)
        })
        .catch((error) => {
          this.customerDetails = "";
          console.log(error);
        });
    },
    onAccountSelect(event){
      console.log(event.target.value)
      this.transactions = this.customerDetails.accounts[event.target.value].transactions
      console.log('Set Trans is' + this.transactions)
    },
    resetForm() {
      this.customerId = "";
      this.initialCredit = "";
      this.isAccountCreated = false;
      this.selectedAccount = "";
      this.initialCredit = "";
      this.isAccountCreated = false;
      this.transactions = []
    }
  },
};
</script>
<style scoped>
.container {
  margin-top: 4rem;
}
.btn {
  margin-right: 2rem;
}
#viewdetails {
  margin-top: 0.7rem;
}
.table-condensed {
  font-size: 11px;
}
.acc-success {
  margin-top: 2rem;
}
.btn-group.accountType {
  width: 400px;
}
</style>