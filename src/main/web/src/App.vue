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
                  v-model="selectedAccount"
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
                    >Account Created</span
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
            <form class="needs-validation" novalidate="">
              <div class="alert alert-danger d-none">
                Please review the problems below:
              </div>
              <div class="row">
                <div class="col-sm-6">
                  <div class="form-floating mb-3">
                    <input
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
                    type="submit"
                    class="btn btn-primary"
                    id="viewdetails"
                  >
                    View Details
                  </button>
                </div>
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
                        <th scope="row">100000</th>
                        <td>Mark</td>
                        <td>Otto</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="row">
                  <table
                    class="table table-striped table-hover table-condensed"
                  >
                    <thead>
                      <tr>
                        <th scope="col">Account Number</th>
                        <th scope="col">Account Type</th>
                        <th scope="col">Account Balance(€)</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <th scope="row">125458523659856247</th>
                        <td>SAVINGS</td>
                        <td>30000</td>
                      </tr>
                      <tr>
                        <th scope="row">458623523659856247</th>
                        <td>CURRENT</td>
                        <td>90000</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="row">
                  <table
                    class="table table-striped table-hover table-condensed"
                  >
                    <thead>
                      <tr>
                        <th scope="col">Transaction Id</th>
                        <th scope="col">Account Number</th>
                        <th scope="col">Transaction Type</th>
                        <th scope="col">Transaction Amount</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <th scope="row">100012</th>
                        <td>125458523659856247</td>
                        <td>CREDIT</td>
                        <td>25000</td>
                      </tr>
                    </tbody>
                  </table>
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
      selectedAccount: "",
      initialCredit: "",
      isAccountCreated: false,
    };
  },
  methods: {
    createAccount() {
      console.log("This is test");
      console.log(this.customerId);
      console.log(this.initialCredit);
      console.log("Seelcted account is " + this.selectedAccount);

      fetch("/api/account/create", {
        method: "post",
        headers: {
          Accept: "application/json, text/plain, */*",
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          customerId: this.customerId,
          initialCredit: this.initialCredit,
          accountType: this.accountTypes[this.selectedAccount],
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
        .catch((error) => {
          this.isAccountCreated = false;
          console.log("FFFFFFFFFFFFFFFFFFFFFFFFFFFFF" + error);
        });
    },
    resetForm() {
      this.customerId = "";
      this.initialCredit = "";
      this.isAccountCreated = false;
    },
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
</style>